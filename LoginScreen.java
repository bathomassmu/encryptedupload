import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.*;
import javax.swing.*;


public class LoginScreen {

	JFrame frameNewUser;
	private JTextField textUsername;
	private JTextField textPword;
	private JTextField textRePword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frameNewUser.setVisible(true);
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
	public LoginScreen() {
		connection=sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameNewUser = new JFrame();
		frameNewUser.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frameNewUser.setResizable(false);
		frameNewUser.setTitle("New User");
		frameNewUser.setBounds(100, 100, 450, 265);
		frameNewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNewUser.getContentPane().setLayout(null);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setBounds(25, 20, 84, 16);
		frameNewUser.getContentPane().add(labelUsername);
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(25, 40, 400, 26);
		frameNewUser.getContentPane().add(textUsername);
		
		JLabel labelPword = new JLabel("Password");
		labelPword.setBounds(25, 78, 61, 16);
		frameNewUser.getContentPane().add(labelPword);
		
		textPword = new JPasswordField("");
		textPword.setColumns(10);
		textPword.setBounds(25, 97, 400, 26);
		frameNewUser.getContentPane().add(textPword);
		
		JLabel labelRePword = new JLabel("Re-enter Password");
		labelRePword.setBounds(25, 135, 150, 16);
		frameNewUser.getContentPane().add(labelRePword);
		
		textRePword = new JPasswordField("");
		textRePword.setColumns(10);
		textRePword.setBounds(25, 153, 400, 26);
		frameNewUser.getContentPane().add(textRePword);
		
		//OK button
		JButton buttonOK = new JButton("OK");
		buttonOK.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String Username = textUsername.getText();
				String pWord = textPword.getText();
				String rePword = textRePword.getText();
				int hpWord = 0; 
				//if passwords do not match
				if (!(pWord.equals(rePword))) { //else
					JOptionPane.showMessageDialog(frameNewUser,"Passwords do not match.", "Error", JOptionPane.WARNING_MESSAGE);
					textPword.setText("");
					textRePword.setText("");
					return;
				}	
            	//	hash PWord
                hpWord = pWord.hashCode(); 
    
                try{
                    String query="INSERT into Login (Username, Password) values (?,?)";
                    PreparedStatement pst=connection.prepareStatement (query);
                    pst.setString(1,textUsername.getText() );
                    pst.setInt(2, hpWord);
                    
                    pst.execute();
                    
                    JOptionPane.showMessageDialog(null, "Data Saved.");
                    
                    pst.close();
                
                }catch(Exception e1)
                {
                    JOptionPane.showMessageDialog(null, e1);    
                }

				frameNewUser.dispose();
				//launch login screen
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		buttonOK.setBounds(196, 195, 117, 29);
		frameNewUser.getContentPane().add(buttonOK);

		//Cancel button
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//exit screen
				frameNewUser.dispose();
				//launch login screen
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		buttonCancel.setBounds(308, 195, 117, 29);
		frameNewUser.getContentPane().add(buttonCancel);
	}

}


//Old OK btn 
/*
//query database for Username value

//if Username exists in database
//	if {
//		JOptionPane.showMessageDialog(frameNewUser,"Username already exists.", "Error", JOptionPane.WARNING_MESSAGE);
//		textUsername.setText(""); 
//		textPword.setText("");
//		textRePword.setText("");
//		return;
//	}


//create new record
//	User.username = local username

//hash pword
hpWord = pWord.hashCode(); 

//save hashed password in the database
//	User.pword = hpWord

//exit screen
 * 
 */