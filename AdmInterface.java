package athirinaProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdmInterface {

	public JFrame frmAdministrator;

	

	/**
	 * Create the application.
	 */
	public AdmInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministrator = new JFrame();
		frmAdministrator.setTitle("Administrator");
		frmAdministrator.getContentPane().setBackground(Color.DARK_GRAY);
		frmAdministrator.setBounds(100, 100, 450, 300);
		frmAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministrator.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("See Salary Expenses");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdmSalaryExpenses window = new AdmSalaryExpenses();
							window.frmAdministrator.setVisible(true);
							frmAdministrator.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(122, 153, 186, 34);
		frmAdministrator.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Submit Returned Prints");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdmReturnedPrints window = new AdmReturnedPrints();
							window.frmAdministrator.setVisible(true);
							frmAdministrator.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(122, 57, 186, 34);
		frmAdministrator.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log Out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login window = new login();
				window.frame.setVisible(true);	
				frmAdministrator.dispose();
			}
		});
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setBounds(0, 227, 89, 23);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorderPainted(false);
		frmAdministrator.getContentPane().add(btnNewButton_2);
	}
}
