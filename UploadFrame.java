import java.awt.EventQueue;

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
import java.io.File;

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

	/**
	 * Create the application.
	 */
	public UploadFrame() {
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
				    //selectedFile.getAbsolutePath());
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
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sDrive = "";
				String sFile = ""; 
				
			//query for selected file
				
			//query drop down menu
			//	if (comboDriveSelect.getSelectedItem() = "Dropbox"){
				
			//	}
			//	else if (comboDriveSelect.getSelectedItem() = "Google Drive"){
			//	}
			//	else if (comboDriveSelect.getSelectedItem() = "Box"){
			//	}

							
				//fileDB.key = (encrypt filename+user.masterpassword)
				
				//encrypt file with fileDB.key
				
				//upload encrypted file to selected dropbox/drive
				
				//decrypt selected file with fileDB.key
			
				//download file to local drive
				
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
