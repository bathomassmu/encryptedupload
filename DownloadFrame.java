import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import java.io.File;

public class DownloadFrame extends JFrame {

	private JPanel paneDecryptionDL;
	JFileChooser fileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DownloadFrame frame = new DownloadFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DownloadFrame() {
		setTitle("Decryption Download");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 135);
		paneDecryptionDL = new JPanel();
		paneDecryptionDL.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneDecryptionDL);
		paneDecryptionDL.setLayout(null);
		
		JLabel lblSelectCloudDrive = new JLabel("Select Cloud Drive");
		lblSelectCloudDrive.setBounds(16, 16, 126, 16);
		paneDecryptionDL.add(lblSelectCloudDrive);
		
		String[] cloudDrives = { "", "Dropbox", "Google Drive", "Box" };
		JComboBox comboBoxSelect = new JComboBox(cloudDrives);
		comboBoxSelect.setSelectedIndex(0);
		comboBoxSelect.setBounds(6, 33, 213, 27);
		paneDecryptionDL.add(comboBoxSelect);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//launch cloud drive dialog to select file from drive
			//query drop down menu
			//	if (comboDriveSelect.getSelectedItem() = "Dropbox"){
					
			//	}
			//	else if (comboDriveSelect.getSelectedItem() = "Google Drive"){
				
			//	}
			//	else if (comboDriveSelect.getSelectedItem() = "Box"){
				
			//	}

	
			}
		});
		btnSelectFile.setBounds(222, 32, 117, 29);
		paneDecryptionDL.add(btnSelectFile);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//launch local save dialog to select location to save to
				int result = fileChooser.showSaveDialog(btnDownload);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
				    //selectedFile.getAbsolutePath());
				}
			//decrypt selected file
				
			//save to selected directory
				
			//open folder the file was saved to
				
			//exit this screen
				dispose();
			//return to Select Upload/Download screen
				ULDLSelect frame = new ULDLSelect();
				frame.setVisible(true);
			}
		});
		btnDownload.setBounds(111, 73, 117, 29);
		paneDecryptionDL.add(btnDownload);
		
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//return to Select Upload/Download screen	
				dispose();
			}
		});
		buttonCancel.setBounds(222, 73, 117, 29);
		paneDecryptionDL.add(buttonCancel);
	}
}
