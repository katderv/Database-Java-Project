package athirinaProject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.String;
import java.sql.*;
import javax.swing.JPasswordField;


public class login {

	public JFrame frame;
	public JTextField Jemail;
	public static String txtEmail;
	private JPasswordField Jpassword;
	public static Connection myConn;

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(72, 35, 120, 41);
		frame.getContentPane().add(lblNewLabel);
		
		Jemail = new JTextField();
		Jemail.setBackground(Color.LIGHT_GRAY);
		Jemail.setBounds(20, 137, 228, 20);
		frame.getContentPane().add(Jemail);
		Jemail.setColumns(50);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 112, 66, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(20, 168, 66, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
			txtEmail = Jemail.getText();
			String txtPassword = Jpassword.getText();
			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/athirina?useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "1234");
				Statement Stmt = myConn.createStatement();
		
				ResultSet myRs1 = Stmt.executeQuery("select first_name from publicer where '" + txtEmail + "'= email and '"+ txtPassword + "' = password" );
				
				 while (myRs1.next()) {
					 EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									PublicerInterface window = new PublicerInterface();
									window.frmPublicer.setVisible(true);
									frame.dispose();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
			       }
				
				 ResultSet myRs2 = Stmt.executeQuery("select adm_name from administrators where '" + txtEmail + "'= email and '"+ txtPassword + "' = password" );
					
				 while (myRs2.next()) {
					 EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									AdmInterface window = new AdmInterface();
									window.frmAdministrator.setVisible(true);
									frame.dispose();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
			       }
				 ResultSet myRs3 = Stmt.executeQuery("select chief_email from chief_editors where '" + txtEmail + "'= chief_email and '"+ txtPassword + "' = password" );
					
				 while (myRs3.next()) {
					 EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									ChiefInterface window = new ChiefInterface();
									window.frmChief.setVisible(true);
									frame.dispose();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
			       }
				 ResultSet myRs4 = Stmt.executeQuery("select email from journalists where '" + txtEmail + "'= email  and '"+ txtPassword + "' = password" );
				
				 while (myRs4.next()) {					 
					 
					 EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									
									JournalistInterface window = new JournalistInterface();
									window.frmJournalist.setVisible(true);
									frame.dispose();
									
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
			       }
				
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
			
			
		}
		}
		);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(88, 251, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		Jpassword = new JPasswordField();
		Jpassword.setBackground(Color.LIGHT_GRAY);
		Jpassword.setBounds(20, 193, 228, 20);
		frame.getContentPane().add(Jpassword);
		frame.setBounds(100, 100, 284, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		);
		
	
	}
}

