package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicerIssuePrints {

	public JFrame frmPublicer;
	public static String Newsp;
	private ArrayList<Integer> list=new ArrayList<Integer>();

	/**
	 * Create the application.
	 */
	public PublicerIssuePrints() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPublicer = new JFrame();
		frmPublicer.setTitle("Publicer");
		frmPublicer.getContentPane().setBackground(Color.DARK_GRAY);
		frmPublicer.setBounds(100, 100, 413, 236);
		frmPublicer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPublicer.getContentPane().setLayout(null);
		
		try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select name from newspaper where owner = '"+login.txtEmail+"'");
			
			
			 while (myResult.next()) {
				 Newsp = myResult.getString("name");
		       }	
			 
			 Statement Stmt2 = login.myConn.createStatement();
			 ResultSet myResult2 = Stmt2.executeQuery("select issue_number from issue where in_newspaper = '"+ Newsp +"'");
			 
			 while (myResult2.next()) {				 
				 list.add(myResult2.getInt("issue_number"));
		       }
			
		       }catch(Exception exc) {
					exc.printStackTrace();
				}
		
		JLabel lblNewLabel = new JLabel("Select Issue:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 219, 14);
		frmPublicer.getContentPane().add(lblNewLabel);

		Integer[] array = list.toArray(new Integer[0]);
		JComboBox comboBox = new JComboBox(array);
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(10, 36, 134, 20);
		frmPublicer.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Insert Number Of Prints:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 67, 190, 14);
		frmPublicer.getContentPane().add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setForeground(Color.LIGHT_GRAY);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBackground(Color.LIGHT_GRAY);
		spinner.setBounds(10, 92, 71, 20);
		frmPublicer.getContentPane().add(spinner);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PublicerInterface window = new PublicerInterface();
				window.frmPublicer.setVisible(true);
				frmPublicer.dispose();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(298, 163, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Insert");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement Stmt = login.myConn.createStatement();
					int myResult = Stmt.executeUpdate("update issue set num_of_prints = '"+(int)spinner.getValue()+"',got_sold = null where issue_number = '"+String.valueOf(comboBox.getSelectedItem())+"'");
					
					JOptionPane.showMessageDialog(frmPublicer,"Insertion Successful!");
				       }catch(Exception exc) {
							exc.printStackTrace();
						}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(199, 163, 89, 23);
		frmPublicer.getContentPane().add(btnNewButton_1);
	}
}
