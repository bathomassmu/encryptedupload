import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ULDLSelect extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ULDLSelect frame = new ULDLSelect();
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
	public ULDLSelect() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnUpload = new JRadioButton("Upload");
		buttonGroup.add(rdbtnUpload);
		rdbtnUpload.setSelected(true);
		rdbtnUpload.setBounds(20, 20, 141, 23);
		contentPane.add(rdbtnUpload);
		
		JRadioButton rdbtnDownload = new JRadioButton("Download ");
		buttonGroup.add(rdbtnDownload);
		rdbtnDownload.setBounds(20, 55, 141, 23);
		contentPane.add(rdbtnDownload);
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnUpload.isSelected()) {
				//exit
					dispose();
				//launch upload screen
					UploadFrame window = new UploadFrame();
					window.paneEncryptedUL.setVisible(true);
				}
				else if (rdbtnDownload.isSelected()) {
				//exit
					dispose();
				//launch download screen	
					DownloadFrame frame = new DownloadFrame();
					frame.setVisible(true);				
				}
			}
		});
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnOK.setBounds(25, 100, 117, 29);
		contentPane.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(135, 100, 117, 29);
		contentPane.add(btnCancel);
	}
}
