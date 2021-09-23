package athirinaProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ChiefInterface {

	public JFrame frmChief;
	public static String Newspaper;

	
	/**
	 * Create the application.
	 */
	public ChiefInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChief = new JFrame();
		frmChief.setTitle("Editor In Chief");
		frmChief.getContentPane().setBackground(Color.DARK_GRAY);
		frmChief.setBounds(100, 100, 491, 312);
		frmChief.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChief.getContentPane().setLayout(null);
		
		try {
		Statement Stmt = login.myConn.createStatement();
		ResultSet myResult = Stmt.executeQuery("select at_newspaper_jrnlst from journalists inner join chief_editors on chief_email = '"+login.txtEmail+"' and chief_email = email;");
		
		 while (myResult.next()) {
			 Newspaper = myResult.getString("at_newspaper_jrnlst");
	       }
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Review Articles");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChiefArtRevie window = new ChiefArtRevie();
							window.frmEditorInChief.setVisible(true);
							frmChief.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(132, 11, 200, 35);
		frmChief.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create New Category");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChiefNewCateg window = new ChiefNewCateg();
							window.frmEditorInChief.setVisible(true);
							frmChief.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(132, 227, 200, 35);
		frmChief.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Submit New Article");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChiefNewArt window = new ChiefNewArt();
							window.frame.setVisible(true);
							frmChief.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(132, 155, 200, 35);
		frmChief.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Set Order Of Articles In issue");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChiefArticleForIssue window = new ChiefArticleForIssue();
							window.frmEditorInChief.setVisible(true);
							frmChief.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setBounds(132, 83, 200, 35);
		frmChief.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login window = new login();
				window.frame.setVisible(true);	
				frmChief.dispose();
			}
		});
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(Color.DARK_GRAY);
		btnNewButton_4.setBounds(0, 239, 89, 23);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBorderPainted(false);
		frmChief.getContentPane().add(btnNewButton_4);
	}
}
