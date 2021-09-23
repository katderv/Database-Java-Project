package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubmittedArticles {

	public JFrame frmJournalist;
	private ButtonGroup group = new ButtonGroup();
	private int i =0;
	

	/**
	 * Create the application.
	 */
	public SubmittedArticles() {
		initialize();
	}
	
	String getSelectedButton(ButtonGroup group)
	{  
	    for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {
	                return button.getText();
	        }
	    }
	    return null;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJournalist = new JFrame();
		frmJournalist.setTitle("Journalist");
		frmJournalist.getContentPane().setBackground(Color.DARK_GRAY);
		frmJournalist.setBounds(100, 100, 450, 300);
		frmJournalist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJournalist.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose Submitted Article to see details:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 385, 14);
		frmJournalist.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JournalistInterface window = new JournalistInterface();
				window.frmJournalist.setVisible(true);
				frmJournalist.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(335, 227, 89, 23);
		frmJournalist.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JournArticleDetails.answer = getSelectedButton(group);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							JournArticleDetails window = new JournArticleDetails();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(236, 227, 89, 23);
		frmJournalist.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log Out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login window = new login();
				window.frame.setVisible(true);	
				frmJournalist.dispose();
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(10, 227, 89, 23);
		frmJournalist.getContentPane().add(btnNewButton_2);
		
		try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select title from article join submits on submits.writer = '"+login.txtEmail+"' and submits.article = article.path_");
				
			
			 while (myResult.next()) {
				 String Name = myResult.getString("title");
				 JRadioButton rdbtnNewRadioButton = new JRadioButton(Name);
				 rdbtnNewRadioButton.setForeground(Color.WHITE);
				 rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
				 rdbtnNewRadioButton.setBounds(20, 35+26*i, 300, 23);
				 rdbtnNewRadioButton.setFocusPainted(false);
				 frmJournalist.getContentPane().add(rdbtnNewRadioButton);
				 group.add(rdbtnNewRadioButton);
				 i++;
		       }		
			

		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
