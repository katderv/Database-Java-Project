package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetOrderOfArticles {

	public JFrame frmDf;
	private String tmp = "";
	private ArrayList<String> list=new ArrayList<String>();
	private String path = "";
	private int pag;
	private int iss;


	/**
	 * Create the application.
	 */
	public SetOrderOfArticles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDf = new JFrame();
		frmDf.setTitle(ChiefArticleForIssue.answer);
		frmDf.getContentPane().setBackground(Color.DARK_GRAY);
		frmDf.setBounds(100, 100, 397, 195);
		frmDf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDf.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issue:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 11, 66, 14);
		frmDf.getContentPane().add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setForeground(Color.LIGHT_GRAY);
		spinner.setBackground(Color.LIGHT_GRAY);
		spinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
		spinner.setBounds(126, 36, 44, 20);
		frmDf.getContentPane().add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("Order:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(126, 11, 96, 14);
		frmDf.getContentPane().add(lblNewLabel_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChiefInterface window = new ChiefInterface();
				window.frmChief.setVisible(true);
				frmDf.dispose();
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(282, 122, 89, 23);
		frmDf.getContentPane().add(btnNewButton_2);
		
		
		
		try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select path_ from article where title = '"+ChiefArticleForIssue.answer+"';");
			
			 while (myResult.next()) {
				 String pathTemp = myResult.getString("path_");
				 String[] arrOfStr = pathTemp.split("\\\\", 0); 
				 for (String a : arrOfStr)
				 {
					 tmp += a + "\\\\";
				 }
				 StringBuilder sb = new StringBuilder(tmp);
				 sb.deleteCharAt(tmp.length()-1);
				 sb.deleteCharAt(tmp.length()-2);
				 path = sb.toString();
					
				 Statement Stmt2 = login.myConn.createStatement();
					ResultSet myResult2 = Stmt2.executeQuery("select issue_number from issue where in_newspaper = '"+ChiefInterface.Newspaper+"';");
					 
					while (myResult2.next()) {
						 list.add(myResult2.getString("issue_number"));
				       }
				 
		       }
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		
		String[] array = list.toArray(new String[0]);
		JComboBox comboBox = new JComboBox(array);
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(20, 36, 96, 20);
		frmDf.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("See Articles In Selected Issue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IssueArticles.issue = String.valueOf(comboBox.getSelectedItem());
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							IssueArticles window = new IssueArticles();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(20, 75, 233, 23);
		frmDf.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add In Issue");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String issue = String.valueOf(comboBox.getSelectedItem());	
					int order = (int) spinner.getValue();
					
					Statement Stmt2 = login.myConn.createStatement();
					ResultSet myResult2 = Stmt2.executeQuery("select order_num from sets_order join article on path_ = sets_order.article where in_issue = '"+issue+"';");
					 
					while(myResult2.next()) {
						int ord = myResult2.getInt("order_num");
						if(ord == order) {
							JOptionPane.showMessageDialog(frmDf, "This place is taken!","Inane warning",JOptionPane.WARNING_MESSAGE);
							return;
						}
						else {
							continue;
						}
									
					}
					
					Statement Stmt3 = login.myConn.createStatement();
					ResultSet myResult3 = Stmt3.executeQuery("SELECT article FROM sets_order WHERE article = '"+path+"';");
					
					
					while(myResult3.next()) {
						Statement Stmt4 = login.myConn.createStatement();
						int myResult4 = Stmt4.executeUpdate("update sets_order set order_num = '"+order+"' where article = '"+path+"'");
						
					}
					
					Statement Statem = login.myConn.createStatement();
					ResultSet myResul = Statem.executeQuery("select in_issue from article where path_ = '"+path+"'");
					
					while(myResul.next()) {
						iss = myResul.getInt("in_issue");
					}

					Statement Stmt6 = login.myConn.createStatement();
					int myResult6 = Stmt6.executeUpdate("update article set in_issue = '"+issue+"' where path_ = '"+path+"';");
					

					Statement Stmt8 = login.myConn.createStatement();
					ResultSet myResult8 = Stmt8.executeQuery("select num_of_pages from article where path_ = '"+path+"';");
					
					while(myResult8.next()) {
						pag = myResult8.getInt("num_of_pages");
					}
					
					if(myResult6 != 0) {
						Statement Stmt7 = login.myConn.createStatement();
						int myResult7 = Stmt7.executeUpdate("update issue set pages_left = pages_left + '"+pag+"'  where issue_number= '"+iss+"';");
						
					}
					
						Statement Stmt5 = login.myConn.createStatement();
						int myResult5 = Stmt5.executeUpdate("insert into sets_order values('"+path+"','"+order+"','"+login.txtEmail+"')");
						
						
					
						JOptionPane.showMessageDialog(frmDf,"Insertion Successful!");
					
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(133, 122, 139, 23);
		frmDf.getContentPane().add(btnNewButton_1);
		
		
	}
}
