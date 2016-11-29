import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;


public class UploadFrame {

	JFrame paneEncryptedUL;
	JFileChooser fileChooser = new JFileChooser();

//	private final ButtonGroup buttonGroup = new ButtonGroup();
//	private JRadioButton radioUL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadFrame window = new UploadFrame();
					window.paneEncryptedUL.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;

	/**
	 * Create the application.
	 */
	public UploadFrame() {
		connection=sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		paneEncryptedUL = new JFrame("");
		paneEncryptedUL.setTitle("Encrypted Upload");
		paneEncryptedUL.setBounds(100, 100, 420, 131);
		paneEncryptedUL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneEncryptedUL.getContentPane().setLayout(null);
		
		//Button to launch Select File dialog 
  		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.setBounds(8, 30, 117, 29);
		paneEncryptedUL.getContentPane().add(btnSelectFile);
		btnSelectFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//launch dialog to select file from local machine
//				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(btnSelectFile);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
				}			
			}	
		});

		
		JLabel lblSelectCloudDrive = new JLabel("Select cloud drive: ");
		lblSelectCloudDrive.setBounds(145, 10, 125, 16);
		paneEncryptedUL.getContentPane().add(lblSelectCloudDrive);
		
		String[] cloudDrives = { "", "Dropbox", "Google Drive", "Box" };
		JComboBox comboDriveSelect = new JComboBox(cloudDrives);
		comboDriveSelect.setSelectedIndex(0);
		comboDriveSelect.setToolTipText("Select Cloud Drive");
		comboDriveSelect.setBounds(140, 31, 275, 27);
		paneEncryptedUL.getContentPane().add(comboDriveSelect);		
		
		JButton btnOK = new JButton("Upload");
//		btnOK.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)  {
				String sDrive = "";
				String sFile = ""; 
				//int hpWord = pst.getInt(2);
				
				
				
			//query for selected file
				ED ed = new ED("12345"); //***CHANGE THIS***
		        File input = new File ("input.txt");
		        File output = new File ("output.txt");

				try {
					ed.setupEncrypt();
					ed.WriteEncryptedFile(input, output);
					ed.ReadEncryptedFile(input, output);
					
				} 
				catch (Exception e1)
			      {
			          //e.printStackTrace();
			      }
			
			//upload encrypted file to selected dropbox/drive
			//query drop down menu
//			if (comboDriveSelect.getSelectedItem() = "Dropbox") {
				
//			}
//			else if (comboDriveSelect.getSelectedItem() = "Google Drive") {

//			}
//			else if (comboDriveSelect.getSelectedItem() = "Box") {

//			}

							
			//exit
			paneEncryptedUL.dispose();
			//back to ULDL screen
			ULDLSelect frame = new ULDLSelect();
			frame.setVisible(true);
			}				
		});
		btnOK.setBounds(189, 70, 117, 29);
		paneEncryptedUL.getContentPane().add(btnOK);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//exit
				paneEncryptedUL.dispose();
				//back to ULDL screen
				ULDLSelect frame = new ULDLSelect();
				frame.setVisible(true);
			}
		});
		btnCancel.setBounds(298, 70, 117, 29);
		paneEncryptedUL.getContentPane().add(btnCancel);
	}
}
		


	

//Trees
//JScrollPane scrollLocal = new JScrollPane();
//scrollLocal.setBounds(15, 15, 420, 301);
//paneEncryptedUL.getContentPane().add(scrollLocal);

//JScrollPane scrollDrive = new JScrollPane();
//scrollDrive.setBounds(230, 15, 205, 301);
//paneEncryptedUL.getContentPane().add(scrollDrive);

//radioUL = new JRadioButton("Upload");
//buttonGroup.add(radioUL);
//radioUL.setSelected(true);
//radioUL.setToolTipText("Upload");
//radioUL.setBounds(10, 327, 86, 29);
//paneEncryptedUL.getContentPane().add(radioUL);

//JRadioButton radioDL = new JRadioButton("Download");
//buttonGroup.add(radioDL);
//radioDL.setBounds(90, 330, 95, 23);
//paneEncryptedUL.getContentPane().add(radioDL);

//if upload
//if (radioUL.isSelected()) {	
//		if (selectedFile = "") {
//			JOptionPane.showMessageDialog(paneEncryptedUL,"Select a file to upload.", "Error", JOptionPane.WARNING_MESSAGE);
//			return;
//		}
//select file to upload
//}	

//if download
//else if (radioDL.isSelected()) {
//		if (selectedFile = "") {
//			JOptionPane.showMessageDialog(paneEncryptedUL,"Select a directory.", "Error", JOptionPane.WARNING_MESSAGE);
//			return;
//		}
//select directory to upload to
//}
