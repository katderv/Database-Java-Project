package athirinaProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class PublicerSeeSales {

	public JFrame frmPublicer;
	private String fin = "";
	private String Newsp;


	/**
	 * Create the application.
	 */
	public PublicerSeeSales() {
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
			Statement Stmt2 = login.myConn.createStatement();
			
			ResultSet myRs2 = Stmt2.executeQuery("select name from newspaper where owner = '"+login.txtEmail+"'" );
			
			 while (myRs2.next()) {
				 Newsp = myRs2.getString("name");
			 }
			
			
			Statement Stmt = login.myConn.createStatement();
	
			ResultSet myRs1 = Stmt.executeQuery("select issue_number,got_sold,num_of_prints from issue where in_newspaper = '"+Newsp+"'" );
			
			 while (myRs1.next()) {
				int issue = myRs1.getInt("issue_number");
				int sold = myRs1.getInt("got_sold");
				int printed = myRs1.getInt("num_of_prints");
				fin += "Issue: "+issue+" \nPrinted: "+printed+" \nSold: "+sold+"\n\n";
			 }
			 } catch (Exception e) {
					e.printStackTrace();
				}
		
		JTextArea textArea = new JTextArea(fin);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBounds(10, 11, 414, 205);
		frmPublicer.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PublicerInterface window = new PublicerInterface();
				window.frmPublicer.setVisible(true);
				frmPublicer.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(335, 227, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton);
	}
}
