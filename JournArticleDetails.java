package athirinaProject;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JournArticleDetails {

	public JFrame frame;
	public static String answer;
	private String path;
	private String synopsis;
	private String newspaper;
	private String category;
	private String category_name;
	private int pages;
	private int issue;
	private String fin;
	private String tmp = "";
	private String pathTemp;
	public static enum Status {
		  Accepted, Rejected, Needs_Corrections,Check_Pending
	  }
	private Status enumVal;


	
	/**
	 * Create the application.
	 */
	public JournArticleDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			Statement Statement = login.myConn.createStatement();
			ResultSet myResult = Statement.executeQuery("select * from article where '"+answer+"'=title");
			 while (myResult.next()) {
				 pathTemp = myResult.getString("path_");
				 pages = myResult.getInt("num_of_pages");
				 synopsis = myResult.getString("synopsis");
				 issue = myResult.getInt("in_issue");
				 newspaper = myResult.getString("in_newspaper");
				 category = myResult.getString("in_category");
			 }
			 
			 //building the path with the right form
			 String[] arrOfStr = pathTemp.split("\\\\", 0); 
			 for (String a : arrOfStr)
			 {
				 tmp += a + "\\\\";
			 }
			 StringBuilder sb = new StringBuilder(tmp);
			 sb.deleteCharAt(tmp.length()-1);
			 sb.deleteCharAt(tmp.length()-2);
			 path = sb.toString();
			 
			 Statement Statement2 = login.myConn.createStatement();	
			 ResultSet myResult2 = Statement2.executeQuery("select status from checks where '"+path+"'=article");
			 while (myResult2.next()) {
				 enumVal =  Status.valueOf(myResult2.getString("status"));
				 
			 }
			 
			 Statement Statement3 = login.myConn.createStatement();
			 ResultSet myResult3 = Statement3.executeQuery("select name from category where '"+category+"'=code");
			 
			 while (myResult3.next()) {
				 category_name = myResult3.getString("name");
				 
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		 
		
		fin = "Title: \""+answer+"\" \nSaved Path: "+pathTemp+" \nSynopsis: "+synopsis+" \nNumber of pages: "+pages+" \nIn Newspaper: "+newspaper+" \nCategory: "+category_name+" \nStatus: "+enumVal.toString()+" \n";
		if(issue !=0) {
			fin += "In issue: "+issue+"";
		}
		else {
			fin += "In issue: Not in an issue";
		}
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setEditable(false);
		textPane.setText(fin);                        
		
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setBounds(10, 11, 414, 191);
		frame.getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
