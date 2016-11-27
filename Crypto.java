import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


public class Crypto
{
    String mPassword = null;
    public final static int SALT_LEN = 8;
    byte [] mInitVec = null;
    byte [] mSalt = null;
    Cipher mEcipher = null;
    Cipher mDecipher = null;
    private final int KEYLEN_BITS = 128; 
    private final int ITERATIONS = 65536;
    private final int MAX_FILE_BUF = 1024;

    public Crypto (String password)
    {
        mPassword = password;
    }
    public byte [] getSalt ()
    {
        return (mSalt);
    }
    public byte [] getInitVec ()
    {
        return (mInitVec);
    }
    private void Db (String msg)
    {
        System.out.println ("** Crypt ** " + msg);
    }

    public void setupEncrypt () throws NoSuchAlgorithmException, 
                                                           InvalidKeySpecException, 
                                                           NoSuchPaddingException, 
                                                           InvalidParameterSpecException, 
                                                           IllegalBlockSizeException, 
                                                           BadPaddingException, 
                                                           UnsupportedEncodingException, 
                                                           InvalidKeyException
    {
        SecretKeyFactory factory = null;
        SecretKey tmp = null;
         mSalt = new byte [SALT_LEN];
        SecureRandom rnd = new SecureRandom ();
        rnd.nextBytes (mSalt);
        Db ("generated salt :" + Hex.encodeHexString(mSalt));
        factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec (mPassword.toCharArray (), mSalt, ITERATIONS, KEYLEN_BITS);
        tmp = factory.generateSecret (spec);
        SecretKey secret = new SecretKeySpec (tmp.getEncoded(), "AES");
        mEcipher = Cipher.getInstance ("AES/CBC/PKCS5Padding");
        mEcipher.init (Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = mEcipher.getParameters ();
        mInitVec = params.getParameterSpec (IvParameterSpec.class).getIV();
        Db ("mInitVec is :" + Hex.encodeHexString (mInitVec));
    }

    public void setupDecrypt (String initvec, String salt) throws NoSuchAlgorithmException, 
                                                                                       InvalidKeySpecException, 
                                                                                       NoSuchPaddingException, 
                                                                                       InvalidKeyException, 
                                                                                       InvalidAlgorithmParameterException, 
                                                                                       DecoderException
    {
        SecretKeyFactory factory = null;
        SecretKey tmp = null;
        SecretKey secret = null;
        mSalt = Hex.decodeHex (salt.toCharArray ());
       Db ("got salt " + Hex.encodeHexString (mSalt));
        mInitVec = Hex.decodeHex (initvec.toCharArray ());
        Db ("got initvector :" + Hex.encodeHexString (mInitVec));  
        factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(mPassword.toCharArray (), mSalt, ITERATIONS, KEYLEN_BITS);
        tmp = factory.generateSecret(spec);
        secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        mDecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        mDecipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(mInitVec));
    }

    public void WriteEncryptedFile (File input, File output) throws 
                                                                                          IOException, 
                                                                                          IllegalBlockSizeException, 
                                                                                          BadPaddingException
    {
        FileInputStream fin;
        FileOutputStream fout;
        long totalread = 0;
        int nread = 0;
        byte [] inbuf = new byte [MAX_FILE_BUF];

        fout = new FileOutputStream (output);
        fin = new FileInputStream (input);

        while ((nread = fin.read (inbuf)) > 0 )
        {
            Db ("read " + nread + " bytes");
            totalread += nread;
            byte [] trimbuf = new byte [nread];
            for (int i = 0; i < nread; i++)
                trimbuf[i] = inbuf[i];
            byte [] tmp = mEcipher.update (trimbuf);
            if (tmp != null)
                fout.write (tmp);
        }
        byte [] finalbuf = mEcipher.doFinal ();
        if (finalbuf != null)
            fout.write (finalbuf);
        fout.flush();
        fin.close();
        fout.close();
        fout.close ();
        Db ("wrote " + totalread + " encrypted bytes");
    }

    public void ReadEncryptedFile (File input, File output) throws 
                                                                                                                                            IllegalBlockSizeException, 
                                                                                                                                            BadPaddingException, 
                                                                                                                                            IOException
    {
        FileInputStream fin; 
        FileOutputStream fout;
        CipherInputStream cin;
        long totalread = 0;
        int nread = 0;
        byte [] inbuf = new byte [MAX_FILE_BUF];

        fout = new FileOutputStream (output);
        fin = new FileInputStream (input);
        cin = new CipherInputStream (fin, mDecipher);
        while ((nread = cin.read (inbuf)) > 0 )
        {
            Db ("read " + nread + " bytes");
            totalread += nread;
            byte [] trimbuf = new byte [nread];
            for (int i = 0; i < nread; i++)
                trimbuf[i] = inbuf[i];
            fout.write (trimbuf);
        }
        fout.flush();
        cin.close();
        fin.close ();       
        fout.close();   

        Db ("wrote " + totalread + " encrypted bytes");
    }


    public static void main(String [] args) throws FileNotFoundException
    {
        File input = new File ("input.txt");
        File eoutput = new File ("encrypted.aes");
        File doutput = new File ("decrypted.txt");
        String iv = null;
        String salt = null;
        Crypto en = new Crypto ("mypassword");
        Scanner input1 = new Scanner(new File("word.txt"));          
        String in = "   ";         
        in = input1.nextLine();
        //setup encryption cipher using password. print out iv and salt
        try
      {
          en.setupEncrypt ();
          iv = Hex.encodeHexString (en.getInitVec ()).toUpperCase ();
          salt = Hex.encodeHexString (en.getSalt ()).toUpperCase ();
      }
      catch (InvalidKeyException e)
      {
          e.printStackTrace();
      }
      catch (NoSuchAlgorithmException e)
      {
          e.printStackTrace();
      }
      catch (InvalidKeySpecException e)
      {
          e.printStackTrace();
      }
      catch (NoSuchPaddingException e)
      {
          e.printStackTrace();
      }
      catch (InvalidParameterSpecException e)
      {
          e.printStackTrace();
      }
      catch (IllegalBlockSizeException e)
      {
          e.printStackTrace();
      }
      catch (BadPaddingException e)
      {
          e.printStackTrace();
      }
      catch (UnsupportedEncodingException e)
      {
          e.printStackTrace();
      }

        //write out encrypted file
        try
      {
          en.WriteEncryptedFile (input, eoutput);
          System.out.printf ("File encrypted to " + eoutput.getName () + "\niv:" + iv + "\nsalt:" + salt + "\n\n");
      }
      catch (IllegalBlockSizeException e)
      {
          e.printStackTrace();
      }
      catch (BadPaddingException e)
      {
          e.printStackTrace();
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }


        //decrypt file
        Crypto dc = new Crypto ("mypassword");
        try
      {
          dc.setupDecrypt (iv, salt);
      }
      catch (InvalidKeyException e)
      {
          e.printStackTrace();
      }
      catch (NoSuchAlgorithmException e)
      {
          e.printStackTrace();
      }
      catch (InvalidKeySpecException e)
      {
          e.printStackTrace();
      }
      catch (NoSuchPaddingException e)
      {
          e.printStackTrace();
      }
      catch (InvalidAlgorithmParameterException e)
      {
          e.printStackTrace();
      }
      catch (DecoderException e)
      {
          e.printStackTrace();
      }

        // write out decrypted file
        try
      {
          dc.ReadEncryptedFile (eoutput, doutput);
          System.out.println ("decryption finished to " + doutput.getName ());
      }
      catch (IllegalBlockSizeException e)
      {
          e.printStackTrace();
      }
      catch (BadPaddingException e)
      {
          e.printStackTrace();
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
   }


}