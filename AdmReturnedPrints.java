package athirinaProject;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdmReturnedPrints {

	public JFrame frmAdministrator;
	private String Newspaper;
	private ArrayList<String> list=new ArrayList<String>();
	private int prints;
	private int numPrints;

	/**
	 * Create the application.
	 */
	public AdmReturnedPrints() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministrator = new JFrame();
		frmAdministrator.setTitle("Administrator");
		frmAdministrator.getContentPane().setBackground(Color.DARK_GRAY);
		frmAdministrator.setBounds(100, 100, 351, 187);
		frmAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministrator.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose Issue:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 149, 14);
		frmAdministrator.getContentPane().add(lblNewLabel);
		
		try {
			Statement Stmt = login.myConn.createStatement();	
			ResultSet myRs1 = Stmt.executeQuery("select at_newspaper_adm from administrators where email = '"+login.txtEmail+"'" );
			
			 while (myRs1.next()) {
				 Newspaper = myRs1.getString("at_newspaper_adm");				 
		       }
			 
			Statement Stmt2 = login.myConn.createStatement();	
			ResultSet myRs2 = Stmt2.executeQuery("select issue_number,num_of_prints from issue where in_newspaper = '"+Newspaper+"'" );
				
			while (myRs2.next()) {
					 list.add(myRs2.getString("issue_number"));		
					 prints = myRs2.getInt("num_of_prints");
			       }
			 } catch (Exception e) {
					e.printStackTrace();
				}
		  SpinnerModel value = new SpinnerNumberModel(0, null, null, 1);
	      JSpinner spinner = new JSpinner(value);
		spinner.setBounds(213, 36, 58, 20);
		frmAdministrator.getContentPane().add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("Returned Prints:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(213, 11, 188, 14);
		frmAdministrator.getContentPane().add(lblNewLabel_1);
		
		String[] array = list.toArray(new String[0]);
		JComboBox comboBox = new JComboBox(array);
		comboBox.setBounds(10, 36, 109, 20);
		frmAdministrator.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdmInterface window = new AdmInterface();
				window.frmAdministrator.setVisible(true);
				frmAdministrator.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(236, 114, 89, 23);
		frmAdministrator.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if((int) spinner.getValue() > prints) {
						JOptionPane.showMessageDialog(frmAdministrator, "Returned are more than printed!","Inane warning",JOptionPane.WARNING_MESSAGE);
						return;
					}
					String issue = String.valueOf(comboBox.getSelectedItem());
					Statement Stmt = login.myConn.createStatement();	
					ResultSet myRs1 = Stmt.executeQuery("select num_of_prints from issue where issue_number = '"+issue+"'" );
					
					 while (myRs1.next()) {
						 numPrints = myRs1.getInt("num_of_prints");				 
				       }
					
					 int sold = numPrints - (int) spinner.getValue();
					Statement Stmt2 = login.myConn.createStatement();	
					int myRs2 = Stmt2.executeUpdate("update issue set got_sold = '"+sold+"' where issue_number = '"+issue+"'" );
						
					JOptionPane.showMessageDialog(frmAdministrator,"Insertion Successful!");
					AdmInterface window = new AdmInterface();
					window.frmAdministrator.setVisible(true);
					frmAdministrator.dispose();
					
					 } catch (Exception e) {
						 JOptionPane.showMessageDialog(frmAdministrator, "Submission Failed!","Inane warning",JOptionPane.WARNING_MESSAGE);
							
							e.printStackTrace();
						}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(137, 114, 89, 23);
		frmAdministrator.getContentPane().add(btnNewButton_1);
	}
}
