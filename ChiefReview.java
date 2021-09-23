package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ChiefReview {

	public JFrame frmAs;
	private String path;
	private String tmp = "";
	public static String titl;

	

	/**
	 * Create the application.
	 */
	public ChiefReview() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
		Statement Stmt = login.myConn.createStatement();
		ResultSet myResult = Stmt.executeQuery("select path_ from article where title = '"+titl+"';");
		
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
		 path = sb.toString();
		 	 
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		frmAs = new JFrame();
		frmAs.setTitle("Editor In Chief");
		frmAs.getContentPane().setBackground(Color.DARK_GRAY);
		frmAs.setBounds(100, 100, 479, 299);
		frmAs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAs.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Comments:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 69, 143, 14);
		frmAs.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 94, 443, 122);
		textArea.setLineWrap(true);
		frmAs.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement Stmt = login.myConn.createStatement();
					int myRes = Stmt.executeUpdate("UPDATE checks SET date_of_check = NOW(),status = 'Accepted',checked_by = '"+ login.txtEmail+"',comments = '"+textArea.getText() +"' where article = '"+ path +"'");
					ChiefArtRevie window = new ChiefArtRevie();
					window.frmEditorInChief.setVisible(true);
					frmAs.dispose();
					}catch(Exception exc) {
						exc.printStackTrace();
					}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(10, 227, 81, 23);
		frmAs.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reject");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement Stmt = login.myConn.createStatement();
					int myRes = Stmt.executeUpdate("UPDATE checks SET date_of_check = NOW(),status = 'Rejected',checked_by = '"+ login.txtEmail+"',comments = '"+textArea.getText() +"' where article = '"+ path +"'");
					ChiefArtRevie window = new ChiefArtRevie();
					window.frmEditorInChief.setVisible(true);
					frmAs.dispose();
					}catch(Exception exc) {
						exc.printStackTrace();
					}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(265, 227, 89, 23);
		frmAs.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiefArtRevie window = new ChiefArtRevie();
				window.frmEditorInChief.setVisible(true);
				frmAs.dispose();
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(364, 227, 89, 23);
		frmAs.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Needs Corrections");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement Stmt = login.myConn.createStatement();
					int myRes = Stmt.executeUpdate("UPDATE checks SET date_of_check = NOW(),status = 'Needs_Corrections',checked_by = '"+ login.txtEmail+"',comments = '"+textArea.getText() +"' where article = '"+ path +"'");
					ChiefArtRevie window = new ChiefArtRevie();
					window.frmEditorInChief.setVisible(true);
					frmAs.dispose();
					}catch(Exception exc) {
						exc.printStackTrace();
					}
			}
			
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setBounds(101, 227, 154, 23);
		frmAs.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Title: "+titl);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 11, 344, 14);
		frmAs.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Current Status: " +ChiefArtRevie.enumVal.toString());
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 36, 255, 14);
		frmAs.getContentPane().add(lblNewLabel_2);
		
		
	}
}
