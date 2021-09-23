package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import athirinaProject.JournArticleDetails.Status;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicerNewsData {

	public JFrame frmPublicer;
	private JTextField textField;
	private static enum PublFreq {
		daily,weekly,monthly
	  }
	


	/**
	 * Create the application.
	 */
	public PublicerNewsData() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPublicer = new JFrame();
		frmPublicer.setTitle("Publicer");
		frmPublicer.getContentPane().setBackground(Color.DARK_GRAY);
		frmPublicer.setBounds(100, 100, 450, 233);
		frmPublicer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPublicer.getContentPane().setLayout(null);
		
	
		
		
		JLabel lblNewLabel = new JLabel("Insert New Name:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 198, 14);
		frmPublicer.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(10, 36, 304, 20);
		frmPublicer.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Publication Frequency:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 67, 257, 14);
		frmPublicer.getContentPane().add(lblNewLabel_1);
		
		JComboBox<PublFreq> comboBox = new JComboBox<>();
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setModel(new DefaultComboBoxModel<>(PublFreq.values()));
		comboBox.setBounds(10, 92, 119, 20);
		frmPublicer.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("See Current Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PublicerNewspDetails window = new PublicerNewspDetails();
							window.frmPublicer.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(10, 165, 155, 23);
		frmPublicer.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PublicerInterface window = new PublicerInterface();
				window.frmPublicer.setVisible(true);
				frmPublicer.dispose();
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(335, 165, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Statement Stmt = login.myConn.createStatement();
					int myResult = Stmt.executeUpdate("update newspaper set name = '"+textField.getText()+"',publication_frequency = '"+String.valueOf(comboBox.getSelectedItem())+"' where owner = '"+login.txtEmail+"'");
					
					JOptionPane.showMessageDialog(frmPublicer,"Update Successful!");
					PublicerInterface window = new PublicerInterface();
					window.frmPublicer.setVisible(true);
					frmPublicer.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(236, 165, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton_2);
	}
}
