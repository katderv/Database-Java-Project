package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import athirinaProject.JournArticleDetails.Status;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class ChiefArtRevie {

	public JFrame frmEditorInChief;
	private int i = 0;
	private ButtonGroup group = new ButtonGroup();
	public static Status enumVal;
	public static String answr;
	public static String nam;

	
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
	 * Create the application.
	 */
	public ChiefArtRevie() {
		initialize();
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
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiefInterface window = new ChiefInterface();
				window.frmChief.setVisible(true);
				frmEditorInChief.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(335, 227, 89, 23);
		frmEditorInChief.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Review");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChiefReview.titl = getSelectedButton(group);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChiefReview window = new ChiefReview();
							window.frmAs.setVisible(true);
							frmEditorInChief.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBounds(236, 227, 89, 23);
		frmEditorInChief.getContentPane().add(btnNewButton_1);
		
		JTextArea txtrChooseArticleFor = new JTextArea();
		txtrChooseArticleFor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrChooseArticleFor.setText("Choose Article For Review (locked articles are already accepted or rejected):");
		txtrChooseArticleFor.setForeground(Color.WHITE);
		txtrChooseArticleFor.setBackground(Color.DARK_GRAY);
		txtrChooseArticleFor.setBounds(10, 11, 414, 31);
		frmEditorInChief.getContentPane().add(txtrChooseArticleFor);
		
		try {
			
			
			Statement Stmt2 = login.myConn.createStatement();
			ResultSet myResult2 = Stmt2.executeQuery("select title,status from article inner join checks on article.in_newspaper = '"+ChiefInterface.Newspaper+"' and article.path_ = checks.article;");
				
			
			 while (myResult2.next()) {
				 nam = myResult2.getString("title");
				 enumVal =  Status.valueOf(myResult2.getString("status"));
				 JRadioButton rdbtnNewRadioButton = new JRadioButton();
				 if(enumVal.toString() == "Accepted") {
					rdbtnNewRadioButton.setText(nam);
					rdbtnNewRadioButton.setEnabled(false);
				 }
				 else if(enumVal.toString() == "Needs_Corrections") {
					rdbtnNewRadioButton.setText(nam);
					rdbtnNewRadioButton.setEnabled(true);
				 }
				 else if(enumVal.toString() == "Check_Pending") {
						rdbtnNewRadioButton.setText(nam);
						rdbtnNewRadioButton.setEnabled(true);
					 }
				 else if(enumVal.toString() == "Rejected") {
						rdbtnNewRadioButton.setText(nam);
						rdbtnNewRadioButton.setEnabled(false);
					 }
				 
				 rdbtnNewRadioButton.setForeground(Color.WHITE);
				 rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
				 rdbtnNewRadioButton.setBounds(34, 45+26*i, 300, 23);
				 rdbtnNewRadioButton.setFocusPainted(false);
				 frmEditorInChief.getContentPane().add(rdbtnNewRadioButton);
				 group.add(rdbtnNewRadioButton);
				 i++;
		       }		
			

		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
