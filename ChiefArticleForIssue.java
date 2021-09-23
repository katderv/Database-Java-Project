package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiefArticleForIssue {

	public JFrame frmEditorInChief;
	private ButtonGroup group = new ButtonGroup();
	private int i =0;
	public static String answer;


	/**
	 * Create the application.
	 */
	public ChiefArticleForIssue() {
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
		frmEditorInChief = new JFrame();
		frmEditorInChief.setTitle("Editor In Chief");
		frmEditorInChief.getContentPane().setBackground(Color.DARK_GRAY);
		frmEditorInChief.setBounds(100, 100, 450, 300);
		frmEditorInChief.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditorInChief.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Article To Add In Issue:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 297, 14);
		frmEditorInChief.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChiefInterface window = new ChiefInterface();
				window.frmChief.setVisible(true);
				frmEditorInChief.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(335, 227, 89, 23);
		frmEditorInChief.getContentPane().add(btnNewButton);
		
		
		
		try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select title from article join checks where article.path_ = checks.article and status = 'Accepted' and in_newspaper = '"+ ChiefInterface.Newspaper+"' ");
				
			
			 while (myResult.next()) {
				 String title = myResult.getString("title");
				 JRadioButton rdbtnNewRadioButton = new JRadioButton(title);
				 rdbtnNewRadioButton.setForeground(Color.WHITE);
				 rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
				 rdbtnNewRadioButton.setBounds(20, 35+26*i, 300, 23);
				 rdbtnNewRadioButton.setFocusPainted(false);
				 frmEditorInChief.getContentPane().add(rdbtnNewRadioButton);
				 group.add(rdbtnNewRadioButton);
				 i++;
		       }		
			

		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				answer = getSelectedButton(group);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SetOrderOfArticles window = new SetOrderOfArticles();
							window.frmDf.setVisible(true);
							frmEditorInChief.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(236, 227, 89, 23);
		frmEditorInChief.getContentPane().add(btnNewButton_1);
	}
}
