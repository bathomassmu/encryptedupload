import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame {

	private JPanel paneLogin;
	private JTextField textUsername;
	private JTextField textPword;
	private JFrame frameNewUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public Login() {
		connection=sqliteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		paneLogin = new JPanel();
		paneLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneLogin);
		paneLogin.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(25, 15, 84, 16);
		paneLogin.add(lblUsername);
		
		textUsername = new JTextField();
		lblUsername.setLabelFor(textUsername);
		textUsername.setBounds(25, 30, 400, 26);
		paneLogin.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblPword = new JLabel("Password");
		lblPword.setBounds(25, 70, 61, 16);
		paneLogin.add(lblPword);
		
		textPword = new JPasswordField("Password:");
		lblPword.setLabelFor(textPword);
		textPword.setText("");
		textPword.setColumns(10);
		textPword.setBounds(25, 85, 400, 26);
		paneLogin.add(textPword);
		

		JButton buttonOK = new JButton("OK");
		buttonOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pWord = textPword.getText();
				int hpWord = 0; 
				//	hash PWord
				hpWord = pWord.hashCode(); 

				 try{    
	                    String query="select * FROM Login WHERE Username =? AND Password =? ";
	                    PreparedStatement pst=connection.prepareStatement (query);
	                    pst.setString(1,textUsername.getText() );
	                	pst.setInt(2, hpWord );
	                    ResultSet rs=pst.executeQuery();
	                    int count = 0;
	                    while(rs.next()){
	                    count=count+1;    
	                    
	                    }
	                    if(count==1)
	                    {
	                        JOptionPane.showMessageDialog(null, "Username & Password Correct");    
	                        
	                    }
	                    else if (count>1)
	                    {
	                        JOptionPane.showMessageDialog(null, "Duplicate Username");
	                    	textUsername.setText(""); 
	                    	textPword.setText("");
	                    	return;
	                    }
	                    else {
	                        JOptionPane.showMessageDialog(null, "Username & Password NOT Correct Try Again");
	                    	textUsername.setText(""); 
	                    	textPword.setText("");
	                    	return;
	                    }
	                    
	                rs.close();
	                pst.close();
	                }catch(Exception e1)
	                {
	                    JOptionPane.showMessageDialog(null, e1);    
	                }

				 //exit screen
				dispose();
				//launch ULDLSelect screen
				ULDLSelect frame = new ULDLSelect();
				frame.setVisible(true);

			} 
		});
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//close current screen
				dispose();
			//launch new user screen
				LoginScreen window = new LoginScreen();
				window.frameNewUser.setVisible(true);
			}
		});
		btnNewUser.setBounds(25, 130, 117, 29);
		paneLogin.add(btnNewUser);
		buttonOK.setBounds(199, 130, 117, 29);
		paneLogin.add(buttonOK);
		
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		buttonCancel.setBounds(308, 130, 117, 29);
		paneLogin.add(buttonCancel);
		
	}
}
	
	//old OK btn
	/*				String Username = textUsername.getText(); 
	String pWord = textPword.getText();
	int hpWord = 0; 
	//query database for Username
	
	//if Username not found
	//	if {
	//		JOptionPane.showMessageDialog(paneLogin,"Username does not exist.", "Error", JOptionPane.WARNING_MESSAGE);
	//		textUsername.setText(""); 
	//		textPword.setText("");
	//		return;
	//	}


	//	hash PWord
	hpWord = pWord.hashCode(); 
	
	//if hpWord does not match the hashed password in the database
	//if (Username.pword != hpWord) {
	//	JOptionPane.showMessageDialog(paneLogin,"Password is not valid.", "Error", JOptionPane.WARNING_MESSAGE);
	//	textPword.setText("");
	//	return;
	//}
	
	//	send selected record to UploadFrame	
	
	//exit screen
	dispose();
	//launch ULDLSelect screen
	ULDLSelect frame = new ULDLSelect();
	frame.setVisible(true);
}
*/