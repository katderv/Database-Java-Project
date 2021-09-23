package athirinaProject;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicerDefineChief {

	public JFrame frmPublicer;
	private String atNewsp;
	private String chief;
	private ArrayList<String> list=new ArrayList<String>();
	private String oldPass;
	private String NewPass;

	/**
	 * Create the application.
	 */
	public PublicerDefineChief() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPublicer = new JFrame();
		frmPublicer.setTitle("Publicer");
		frmPublicer.getContentPane().setBackground(Color.DARK_GRAY);
		frmPublicer.setBounds(100, 100, 415, 209);
		frmPublicer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPublicer.getContentPane().setLayout(null);
		
		try {
			Statement Stmt = login.myConn.createStatement();
	
			ResultSet myRs1 = Stmt.executeQuery("select name from newspaper where owner ='" + login.txtEmail + "'" );
			
			 while (myRs1.next()) {
				 atNewsp = myRs1.getString("name");
			 }
			 
			Statement Stmt2 = login.myConn.createStatement();
				
			ResultSet myRs2 = Stmt2.executeQuery("select chief_email from chief_editors join journalists on email = chief_email and at_newspaper_jrnlst = '"+atNewsp+"';" );
				
			while (myRs2.next()) {
					 chief = myRs2.getString("chief_email");
			}
			
			Statement Stmt3 = login.myConn.createStatement();
				
			ResultSet myRs3 = Stmt3.executeQuery("select email from journalists where at_newspaper_jrnlst = '"+atNewsp+"';" );
					
			while (myRs3.next()) {
				list.add(myRs3.getString("email"));
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("Select Editor In Chief:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 252, 14);
		frmPublicer.getContentPane().add(lblNewLabel);
		
		String[] array = list.toArray(new String[0]);
		JComboBox comboBox = new JComboBox(array);
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(10, 36, 278, 20);
		frmPublicer.getContentPane().add(comboBox);
		
		String fin = "Current Editor In Chief: "+chief+"";
		
		JTextArea textArea = new JTextArea(fin);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 11));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBounds(10, 67, 332, 45);
		frmPublicer.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PublicerInterface window = new PublicerInterface();
				window.frmPublicer.setVisible(true);
				frmPublicer.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(300, 135, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Define");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NewChief = String.valueOf(comboBox.getSelectedItem());
				try {
				Statement Stmt = login.myConn.createStatement();
				
				ResultSet myRs1 = Stmt.executeQuery("select password from chief_editors where chief_email ='" + chief + "'" );
				
				while (myRs1.next()) {
					oldPass = myRs1.getString("password");
				}
				 
				Statement Stmt2 = login.myConn.createStatement();
					
				int myRs2 = Stmt2.executeUpdate("update journalists set password = '"+oldPass+"' where email = '"+chief+"'" );
								
				Statement Stmt3 = login.myConn.createStatement();
				
				int myRs3 = Stmt3.executeUpdate("delete from chief_editors where chief_email = '"+chief+"'" );
				
				Statement Stmt4 = login.myConn.createStatement();
				
				ResultSet myRs4 = Stmt4.executeQuery("select password from journalists where email ='" + NewChief + "'" );
				
				 while (myRs4.next()) {
					 NewPass = myRs4.getString("password");
				 }
				 
				Statement Stmt5 = login.myConn.createStatement();
					
				int myRs5 = Stmt5.executeUpdate("insert into chief_editors values('"+NewChief+"','"+NewPass+"','"+login.txtEmail+"')" );
				
				Statement Stmt6 = login.myConn.createStatement();
				
				int myRs6 = Stmt6.executeUpdate("update journalists set password = null where email = '"+NewChief+"'" );
				 
				JOptionPane.showMessageDialog(frmPublicer,"Insertion Complete!");
				PublicerInterface window = new PublicerInterface();
				window.frmPublicer.setVisible(true);
				frmPublicer.dispose();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(199, 135, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton_1);
	}
}
