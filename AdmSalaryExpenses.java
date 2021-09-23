package athirinaProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;


public class AdmSalaryExpenses {

	public JFrame frmAdministrator;
	private ArrayList<String> list=new ArrayList<String>();
	private int result = 0;
	private String employee = "";
	private int HireToNow= 0;


	/**
	 * Create the application.
	 */
	public AdmSalaryExpenses() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministrator = new JFrame();
		frmAdministrator.setTitle("Administrator");
		frmAdministrator.getContentPane().setBackground(Color.DARK_GRAY);
		frmAdministrator.setBounds(100, 100, 450, 228);
		frmAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministrator.getContentPane().setLayout(null);
		
		JTextArea txtrInsertNumberOf = new JTextArea();
		txtrInsertNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrInsertNumberOf.setText("Insert Number Of Months To See Salary  Expenses:");
		txtrInsertNumberOf.setForeground(Color.WHITE);
		txtrInsertNumberOf.setBackground(Color.DARK_GRAY);
		txtrInsertNumberOf.setEditable(false);
		txtrInsertNumberOf.setBounds(10, 11, 414, 20);
		frmAdministrator.getContentPane().add(txtrInsertNumberOf);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(10, 42, 58, 20);
		frmAdministrator.getContentPane().add(spinner);
		try {
			Statement Stmt = login.myConn.createStatement();
	
			ResultSet myRs1 = Stmt.executeQuery("select email from journalists" );
			
			 while (myRs1.next()) {
				 list.add(myRs1.getString("email"));
		       }
			 
			Statement Stmt2 = login.myConn.createStatement();
				
			ResultSet myRs2 = Stmt2.executeQuery("select email from administrators" );
				
			while (myRs2.next()) {
					 list.add(myRs2.getString("email"));
			   }
				 
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		String[] array = list.toArray(new String[0]);
		JComboBox comboBox = new JComboBox(array);
		comboBox.setEnabled(false);
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(37, 113, 252, 20);
		frmAdministrator.getContentPane().add(comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected() ) {
					comboBox.setEnabled(true);
				}
				else {
					comboBox.setEnabled(false);
					 
				}
			}
		});
		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox.setBounds(10, 113, 21, 20);
		frmAdministrator.getContentPane().add(chckbxNewCheckBox);
		
		JTextArea txtrChooseTheEmployee = new JTextArea();
		txtrChooseTheEmployee.setEditable(false);
		txtrChooseTheEmployee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrChooseTheEmployee.setText("Choose The Employee You Want To  See Salary Expenses (if you want to see \r\n expenses in total dont select the box):");
		txtrChooseTheEmployee.setBackground(Color.DARK_GRAY);
		txtrChooseTheEmployee.setForeground(Color.WHITE);
		txtrChooseTheEmployee.setBounds(10, 73, 414, 40);
		txtrChooseTheEmployee.setLineWrap(true);
		frmAdministrator.getContentPane().add(txtrChooseTheEmployee);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdmInterface window = new AdmInterface();
				window.frmAdministrator.setVisible(true);
				frmAdministrator.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(335, 155, 89, 23);
		frmAdministrator.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Calculate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = 0;

				int months = (int) spinner.getValue();

				if(chckbxNewCheckBox.isSelected()) {
					employee = String.valueOf(comboBox.getSelectedItem());	
				}
				else {
					employee = null;
				}
				
				if(employee == null) {
					try {
						for (String element : list) {
							Statement Stmt = login.myConn.createStatement();
							
							ResultSet myRs1 = Stmt.executeQuery("select wage,TIMESTAMPDIFF(month, hire_date, NOW()) from journalists where email = '"+element+"'" );
							
							 while (myRs1.next()) {
								 int salary = myRs1.getInt("wage");
								 HireToNow  = myRs1.getInt("TIMESTAMPDIFF(month, hire_date, NOW())");
								 if(HireToNow > months) {
								 result += salary*months;
								 }
								 else {
									 result += salary*HireToNow;
								 }
						       }
							 
							Statement Stmt2 = login.myConn.createStatement();
								
							ResultSet myRs2 = Stmt2.executeQuery("select wage,TIMESTAMPDIFF(month, hire_date, NOW()) from administrators where email = '"+element+"'" );
								
							while (myRs2.next()) {
								int salary = myRs2.getInt("wage");
								 HireToNow  = myRs2.getInt("TIMESTAMPDIFF(month, hire_date, NOW())");
								 if(HireToNow > months) {
									 result += salary*months;
									 }
									 else {
										 result += salary*HireToNow;
									 }
							   }
						}
					}catch(Exception exc) {
						exc.printStackTrace();
					}
				}
				else {
					try {
							Statement Stmt = login.myConn.createStatement();
							
							ResultSet myRs1 = Stmt.executeQuery("select wage,TIMESTAMPDIFF(month, hire_date, NOW()) from journalists where email = '"+employee+"'" );
							
							 while (myRs1.next()) {
								 int salary = myRs1.getInt("wage");
								 HireToNow  = myRs1.getInt("TIMESTAMPDIFF(month, hire_date, NOW())");
								 if(HireToNow > months) {
									 result += salary*months;
									 }
									 else {
										 result += salary*HireToNow;
									 }
						       }
							 
							Statement Stmt2 = login.myConn.createStatement();
								
							ResultSet myRs2 = Stmt2.executeQuery("select wage,TIMESTAMPDIFF(month, hire_date, NOW()) from administrators where email = '"+employee+"'" );
								
							while (myRs2.next()) {
								int salary = myRs2.getInt("wage");
								HireToNow  = myRs2.getInt("TIMESTAMPDIFF(month, hire_date, NOW())");
								 if(HireToNow > months) {
									 result += salary*months;
									 }
									 else {
										 result += salary*HireToNow;
									 }
							   }
						
					}catch(Exception exc) {
						exc.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(frmAdministrator,"Selected Expenses are "+result+" euros");
				
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(236, 155, 89, 23);
		frmAdministrator.getContentPane().add(btnNewButton_1);
		
		
	}
}
