package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicerNewspDetails {

	public JFrame frmPublicer;
	private String Newsp;
	private static enum PublFreq {
		daily,weekly,monthly
	  }
	private PublFreq enumVal;


	/**
	 * Create the application.
	 */
	public PublicerNewspDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPublicer = new JFrame();
		frmPublicer.setTitle("Publicer");
		frmPublicer.getContentPane().setBackground(Color.DARK_GRAY);
		frmPublicer.setBounds(100, 100, 450, 300);
		frmPublicer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPublicer.getContentPane().setLayout(null);
		
	try {
			
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select * from newspaper where owner = '"+login.txtEmail+"';");
			
			 while (myResult.next()) {
				 Newsp = myResult.getString("name");
				 enumVal =  PublFreq.valueOf(myResult.getString("publication_frequency"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		String Fin = "Newspaper Name: "+Newsp+"\nPublication Frequency: "+enumVal.toString()+"";
		JTextArea textArea = new JTextArea(Fin);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBounds(10, 11, 414, 210);
		frmPublicer.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPublicer.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(335, 232, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton);
	}
}
