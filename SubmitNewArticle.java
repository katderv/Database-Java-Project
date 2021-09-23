package athirinaProject;

import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;  
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.lang.reflect.Array;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JTextArea;


public class SubmitNewArticle {

	public JFrame frmJournalist;
	private JTextField textField;
	private JTextField textField_1;
	private ArrayList<String> list=new ArrayList<String>();
	private ArrayList<String> list2=new ArrayList<String>();
	private String newspaper;
	private String path = "";
	private String title = null;
	private int pages;
	private String synopsis;
	private String code;
	private String[] keywords = null;
	private String[] images = null;
	private String tmp = "";
	private String tmp2 = "";
	private int counter = 0;

	/**
	 * Create the application.
	 */
	public SubmitNewArticle() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJournalist = new JFrame();
		frmJournalist.getContentPane().setForeground(Color.RED);
		frmJournalist.setTitle("Journalist");
		frmJournalist.getContentPane().setBackground(Color.DARK_GRAY);
		frmJournalist.setBounds(100, 100, 451, 474);
		frmJournalist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJournalist.getContentPane().setLayout(null);
		frmJournalist.getContentPane().setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Insert Path:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 126, 14);
		frmJournalist.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(10, 36, 415, 20);
		frmJournalist.getContentPane().add(textField);
		textField.setColumns(300);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Insert Title:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 67, 176, 14);
		frmJournalist.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(10, 92, 415, 20);
		frmJournalist.getContentPane().add(textField_1);
		textField_1.setColumns(300);
		
		JLabel lblNewLabel_2 = new JLabel("Number Of Pages:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 123, 126, 14);
		frmJournalist.getContentPane().add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
		spinner.setForeground(Color.LIGHT_GRAY);
		spinner.setBackground(Color.CYAN);
		spinner.setBounds(10, 148, 39, 20);
		frmJournalist.getContentPane().add(spinner);
		
		JLabel lblNewLabel_3 = new JLabel("Synopsis:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(141, 123, 176, 14);
		frmJournalist.getContentPane().add(lblNewLabel_3);
		
		
		
		
		try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select name from category");
			
			
			 while (myResult.next()) {
				 list.add(myResult.getString("name"));
		       }	
			 
			 ResultSet myResult2 = Stmt.executeQuery("select at_newspaper_jrnlst from journalists where email = '"+ login.txtEmail +"'");
			 
			 while (myResult2.next()) {
				 newspaper = myResult2.getString("at_newspaper_jrnlst");
		       }
			 ResultSet myResult3 = Stmt.executeQuery("select email from journalists where at_newspaper_jrnlst = '"+ newspaper +"'");
			 
			 while (myResult3.next()) {
				 if(login.txtEmail.equals(myResult3.getString("email")) == false) {
				 list2.add(myResult3.getString("email"));
				 }
		       }
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		String[] array = list.toArray(new String[0]);
		JComboBox comboBox = new JComboBox(array);
		comboBox.setMaximumRowCount(100);
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(10, 232, 109, 20);
		frmJournalist.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Choose Category:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(10, 211, 145, 14);
		frmJournalist.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmJournalist.dispose();
				JournalistInterface window = new JournalistInterface();
				window.frmJournalist.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(336, 401, 89, 23);
		frmJournalist.getContentPane().add(btnNewButton);
		
		String[] array2 = list2.toArray(new String[0]);
		
		JList list_1 = new JList(array2);
		list_1.setBackground(Color.LIGHT_GRAY);
		list_1.setEnabled(false);
		list_1.setBounds(172, 232, 253, 20);
		frmJournalist.getContentPane().add(list_1);		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");		
		chckbxNewCheckBox.setForeground(Color.BLACK);
		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxNewCheckBox.isSelected() ) {
					list_1.setEnabled(true);
					list_1.setBounds(172, 232, 253, 20*array2.length);
				}
				else {
					list_1.setEnabled(false);
					list_1.setBounds(172, 232, 253, 20);
					list_1.clearSelection();
				}
			}
		});
		
		chckbxNewCheckBox.setBounds(141, 232, 21, 20);
		frmJournalist.getContentPane().add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_5 = new JLabel("Insert Other Writers If Exist: ");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(141, 210, 228, 14);
		frmJournalist.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Insert Keywords (use space among keywords):");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(10, 259, 415, 14);
		frmJournalist.getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_2 = new JButton("Hide list");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list_1.getHeight() != 20) {
				list_1.setBounds(172, 232, 253, 20);
				}
				else {
					list_1.setBounds(172, 232, 253, 20*array2.length);
				}
			}
		});
		
		btnNewButton_2.setBounds(336, 207, 89, 23);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorderPainted(false);
		frmJournalist.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_7 = new JLabel("Insert Paths of Images for the article(use space among paths):");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(10, 325, 359, 14);
		frmJournalist.getContentPane().add(lblNewLabel_7);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(141, 137, 284, 62);
		textArea.setLineWrap(true);
		frmJournalist.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(Color.LIGHT_GRAY);
		textArea_1.setBounds(10, 277, 415, 37);
		textArea_1.setLineWrap(true);
		frmJournalist.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setBackground(Color.LIGHT_GRAY);
		textArea_2.setBounds(34, 346, 391, 46);
		textArea_2.setLineWrap(true);
		frmJournalist.getContentPane().add(textArea_2);
		
		
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox_1.setBounds(10, 346, 21, 23);
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox_1.isSelected() ) {
					textArea_2.setEditable(true);
				}
				else {
					textArea_2.setText(null);
					textArea_2.setEditable(false);
				}
			}
		});
		frmJournalist.getContentPane().add(chckbxNewCheckBox_1);
		
		
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setBounds(237, 401, 89, 23);
		frmJournalist.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String pathTemp = textField.getText();
				
				String[] arrOfStr = pathTemp.split("\\\\", 0); 
				 for (String a : arrOfStr)
				 {
					 tmp += a + "\\\\";
				 }
				 StringBuilder sb = new StringBuilder(tmp);
				 sb.deleteCharAt(tmp.length()-1);
				 sb.deleteCharAt(tmp.length()-2);
				 path = sb.toString();
				tmp = "";
				 
				title = textField_1.getText();
				pages = (int) spinner.getValue();
				synopsis = textArea.getText();
				String category = String.valueOf(comboBox.getSelectedItem());	
				Collection <String> Selected =list_1.getSelectedValuesList();
				Iterator it = Selected.iterator();
				
				
				String keywordsTmp = textArea_1.getText();
				if(keywordsTmp != null) {
				keywords = keywordsTmp.split(" ",0);
				}
				
				String ImagesTmp = textArea_2.getText();
				images = ImagesTmp.split(" ",0);
				if(images != null) {
				for (String k : images) {
					String[] arrOfStr2 = k.split("\\\\", 0); 
					 for (String b : arrOfStr2)
					 {
						 tmp2 += b + "\\\\";
					 }
					 StringBuilder sb2 = new StringBuilder(tmp2);
					 sb2.deleteCharAt(tmp2.length()-1);
					 sb2.deleteCharAt(tmp2.length()-2);
					 Array.set(images,counter,sb2.toString());
					 arrOfStr2 = null;
					 tmp2 = "";
					counter++;
					
					}

				tmp2 = "";
				}
				if(title.isEmpty()) {
					JOptionPane.showMessageDialog(frmJournalist, "Title is empty!","Inane warning",JOptionPane.WARNING_MESSAGE);
					frmJournalist.dispose();
					JournalistInterface window = new JournalistInterface();
					window.frmJournalist.setVisible(true);
				}
				
				try {
					Statement Statement = login.myConn.createStatement();
				ResultSet myResult = Statement.executeQuery("select code from category where name = '"+category+"'");
				while (myResult.next()) {
						 code = myResult.getString("code");
				      }
					
				
				if(path !=null &&  path.length()!=0 && path.charAt(0) == 'C' && path.charAt(1) == ':' && path.charAt(2) == '\\' ) {
					int myRes = Statement.executeUpdate("insert into article values('"+ path +"', '"+ title +"','"+ pages+"','"+synopsis+"', null, '"+ newspaper +"', '"+code+"');");
					
					for (String m : images) {
						if(m ==null || m.length() == 0) {
							break;
						}
						else if(m.charAt(0) == 'C' && m.charAt(1) == ':' && m.charAt(2) == '\\' ) {
							int myRes6 = Statement.executeUpdate("insert into images values('"+path+"','"+m+"');");
						}
						else {
							int delete = Statement.executeUpdate("delete from article where path_= '"+path+"'");
							path = "";
							
							JOptionPane.showMessageDialog(frmJournalist, "Wrong image path input!","Inane warning",JOptionPane.WARNING_MESSAGE);
							return;
						}
					
				} 
						
				}
				else {
					JOptionPane.showMessageDialog(frmJournalist, "Wrong article path input!","Inane warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				
				int myRes2 = Statement.executeUpdate("insert into submits values('"+path +"','"+login.txtEmail+"', CURDATE());");
				while (it.hasNext()) {
					int myRes3 = Statement.executeUpdate("insert into submits values('"+path +"','"+it.next()+"', CURDATE());");		
					}
				
				try {

					int myRes4 = Statement.executeUpdate("insert into checks values('"+path+"',null, 'Check_Pending',null,null);"); 
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				
				
				
				for (String j : keywords) {
					
						int myRes5 = Statement.executeUpdate("insert into keywords values('"+path+"','"+j+"');");
					
				}
							
				int myRes6 = Statement.executeUpdate("delete from keywords where word = ''");
				int myRes7 = Statement.executeUpdate("delete from images where image_path = ''");
				
				JOptionPane.showMessageDialog(frmJournalist,"Insertion Successful!");
				frmJournalist.dispose();
				JournalistInterface window = new JournalistInterface();
				window.frmJournalist.setVisible(true);
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(frmJournalist, "Wrong input!","Inane warning",JOptionPane.WARNING_MESSAGE);
					
					e.printStackTrace();
				}
				
				
		    } 
			
			}
		
		
		);		
		
	}
}
