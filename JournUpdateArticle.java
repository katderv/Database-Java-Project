package athirinaProject;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

public class JournUpdateArticle {

	public JFrame frmJoutnalist;
	private int i=0;
	private ButtonGroup group = new ButtonGroup();
	public static String answer;
	public static String path;
	private String tmp = "";
	private String pathNew;
	


	/**
	 * Create the application.
	 */
	public JournUpdateArticle() {
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
		frmJoutnalist = new JFrame();
		frmJoutnalist.setTitle("Journalist");
		frmJoutnalist.getContentPane().setBackground(Color.DARK_GRAY);
		frmJoutnalist.setBounds(100, 100, 450, 300);
		frmJoutnalist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJoutnalist.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JournalistInterface window = new JournalistInterface();
				window.frmJournalist.setVisible(true);
				frmJoutnalist.dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		frmJoutnalist.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Choose the article you want to update:");
		lblNewLabel.setBounds(24, 11, 226, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		frmJoutnalist.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Log out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login window = new login();
				window.frame.setVisible(true);	
				frmJoutnalist.dispose();
			}
		});
		btnNewButton_2.setBounds(24, 227, 89, 23);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorderPainted(false);
		frmJoutnalist.getContentPane().add(btnNewButton_2);

		
			try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select title from article join checks join submits on  '" + login.txtEmail + "' = submits.writer and checks.article = article.path_ and checks.status = 'Needs_Corrections' group by title");
				
			
			 while (myResult.next()) {
				 String Name = myResult.getString("title");
				 JRadioButton rdbtnNewRadioButton = new JRadioButton(Name);
				 rdbtnNewRadioButton.setForeground(Color.WHITE);
				 rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
				 rdbtnNewRadioButton.setBounds(34, 45+26*i, 300, 23);
				 rdbtnNewRadioButton.setFocusPainted(false);
				 frmJoutnalist.getContentPane().add(rdbtnNewRadioButton);
				 group.add(rdbtnNewRadioButton);
				 i++;
		       }		
			

		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
			JButton btnNewButton_1 = new JButton("Update");
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					answer = getSelectedButton(group);
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								if(answer == null) {
									JOptionPane.showMessageDialog(frmJoutnalist, "You have not selected an article!","Inane warning",JOptionPane.WARNING_MESSAGE);
								}
								Statement Statement = login.myConn.createStatement();
								ResultSet myResult = Statement.executeQuery("select path_ from article where '"+answer+"'=title");
								 while (myResult.next()) {
									 path = myResult.getString("path_");
								 }
								 String[] arrOfStr = path.split("\\\\", 0); 
								 for (String a : arrOfStr)
								 {
									 tmp += a + "\\\\";
								 }
								 StringBuilder sb = new StringBuilder(tmp);
								 sb.deleteCharAt(tmp.length()-1);
								 sb.deleteCharAt(tmp.length()-2);
								 pathNew = sb.toString();
								 Statement Statement2 = login.myConn.createStatement();
								 int myRes2 = Statement2.executeUpdate("UPDATE submits SET date_of_sub = NOW() where article = '"+ pathNew +"'");
								 int myRes3 = Statement2.executeUpdate("UPDATE checks set status = 'Check_Pending' where article = '"+ pathNew +"'");
								 frmJoutnalist.dispose();
								 JournUpdateArticle window = new JournUpdateArticle();
									window.frmJoutnalist.setVisible(true);
								 
							} catch (Exception e) {
								e.printStackTrace();
								}
							}
					});
				}
			});
			btnNewButton_1.setBounds(236, 227, 89, 23);
			btnNewButton_1.setBackground(Color.LIGHT_GRAY);
			frmJoutnalist.getContentPane().add(btnNewButton_1);
		
			
			
	}
}
