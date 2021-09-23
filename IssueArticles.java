package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IssueArticles {

	public JFrame frame;
	public static String issue;
	private String fin = "";

	/**
	 * Create the application.
	 */
	public IssueArticles() {
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
			
					
				 Statement Stmt2 = login.myConn.createStatement();
					ResultSet myResult2 = Stmt2.executeQuery("select article,order_num,title from sets_order join article on path_ = sets_order.article and in_newspaper = '"+ChiefInterface.Newspaper+"' and article.in_issue = '"+issue+"' order by order_num;");
					 
					while (myResult2.next()) {
						 String article = myResult2.getString("article");
						 String order_num = myResult2.getString("order_num");
						 String title = myResult2.getString("title");
						 fin += "Title: "+title+" \nPath: "+article+" \nPlace: "+order_num+" \n\n";
				       }
				 
		       
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setBounds(10, 11, 414, 209);
		textPane.setText(fin); 
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
