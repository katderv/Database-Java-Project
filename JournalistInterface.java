package athirinaProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class JournalistInterface {

	public JFrame frmJournalist;

	/**
	 * Create the application.
	 */
	public JournalistInterface() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJournalist = new JFrame();
		frmJournalist.setResizable(false);
		frmJournalist.getContentPane().setBackground(Color.DARK_GRAY);
		frmJournalist.setTitle("Journalist");
		frmJournalist.setBounds(100, 100, 450, 300);
		frmJournalist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJournalist.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("See Submitted Articles");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SubmittedArticles window = new SubmittedArticles();
							window.frmJournalist.setVisible(true);
							frmJournalist.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(130, 115, 180, 35);
		btnNewButton_1.setFocusPainted(false);
		frmJournalist.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit Article");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							JournUpdateArticle window = new JournUpdateArticle();
							window.frmJoutnalist.setVisible(true);
							frmJournalist.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(130, 190, 180, 35);
		frmJournalist.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Submit New Article");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SubmitNewArticle window = new SubmitNewArticle();
							window.frmJournalist.setVisible(true);
							frmJournalist.dispose();							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(130, 40, 180, 35);
		frmJournalist.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Log out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login window = new login();
				window.frame.setVisible(true);	
				frmJournalist.dispose();
			}
		});
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.setBounds(10, 11, 89, 23);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBorderPainted(false);
		frmJournalist.getContentPane().add(btnNewButton_3);
	}
}
