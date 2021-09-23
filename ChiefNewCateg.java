package athirinaProject;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiefNewCateg {

	public JFrame frmEditorInChief;
	private JTextField textField;
	private JTree tree;

	

	/**
	 * Create the application.
	 */
	public ChiefNewCateg() {
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
		
		try {
			Statement Stmt = login.myConn.createStatement();
			ResultSet myResult = Stmt.executeQuery("select name,code from category where parent is null order by code;");
			
			
		
			tree = new JTree();
			tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Categories") {
				{
					 while (myResult.next()) {
						 String NoParent = myResult.getString("name");
						 String Code = myResult.getString("code");
						 DefaultMutableTreeNode node = new DefaultMutableTreeNode(NoParent);
						 Statement Stmt2 = login.myConn.createStatement();
						 ResultSet myResult2 = Stmt2.executeQuery("select name,parent from category where parent is not null order by code;");

							while (myResult2.next()) {
								String ParentCode = myResult2.getString("parent");
								if(ParentCode.equals(Code)) {
								 String name = myResult2.getString("name");
								 node.add(new DefaultMutableTreeNode(name));
								}
								
							}						 
							add(node);
				       }
					}
				}
				));
			tree.setBackground(Color.DARK_GRAY);
			tree.setCellRenderer(				
						new DefaultTreeCellRenderer() {					
						public void 	setBackgroundNonSelectionColor(Color newColor) {						
							newColor = Color.DARK_GRAY; } 					
						public Color setTextSelectionColor() {						
							return Color.DARK_GRAY; } 
						public Color getTextNonSelectionColor() {					
							return Color.white; } 
					});
			tree.setBounds(10, 41, 117, 209);
			frmEditorInChief.getContentPane().add(tree);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(166, 74, 215, 20);
		frmEditorInChief.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Category Name:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(166, 49, 219, 14);
		frmEditorInChief.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Description:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(166, 115, 117, 14);
		frmEditorInChief.getContentPane().add(lblNewLabel_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(166, 140, 215, 48);
		frmEditorInChief.getContentPane().add(textPane);
		
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
		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				  if (node == null) {
					  JOptionPane.showMessageDialog(frmEditorInChief, "Nothing Is Selected!","Inane warning",JOptionPane.WARNING_MESSAGE); 
					    return;
				  }

					    String nodeInfo = (String)node.getUserObject();
					    
					    try {
					    	
							if(nodeInfo.equals("Categories")) {
					    		Statement Stmt2 = login.myConn.createStatement();
								int myResult2 = Stmt2.executeUpdate("insert into category values(null,'"+textField.getText()+"','"+textPane.getText()+"',null);");					    		
					    	}
					    	else {
					    		Statement Stmt = login.myConn.createStatement();
								ResultSet myResult = Stmt.executeQuery("select code from category where name = '"+nodeInfo+"';");
						    	while(myResult.next()) {
						    		String code = myResult.getString("code");
						    		Statement Stmt3 = login.myConn.createStatement();
									int myResult3 = Stmt3.executeUpdate("insert into category values(null,'"+textField.getText()+"','"+textPane.getText()+"','"+code+"');");	
						    	}
					    	}
							Statement Stmt5 = login.myConn.createStatement();
							ResultSet myResult5 = Stmt5.executeQuery("select code from category where name = '"+textField.getText()+"';");
							while( myResult5.next()) {
								String code = myResult5.getString("code");
								Statement Stmt4 = login.myConn.createStatement();
								int myResult4 = Stmt4.executeUpdate("insert into creates_category values('"+code+"','"+login.txtEmail+"');");	
							}
							JOptionPane.showMessageDialog(frmEditorInChief,"Insertion Successful!");
							ChiefInterface window = new ChiefInterface();
							window.frmChief.setVisible(true);
							frmEditorInChief.dispose();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(236, 227, 89, 23);
		frmEditorInChief.getContentPane().add(btnNewButton_1);
		
		JTextPane txtpnChooseParentCategory = new JTextPane();
		txtpnChooseParentCategory.setEditable(false);
		txtpnChooseParentCategory.setForeground(Color.WHITE);
		txtpnChooseParentCategory.setText("Choose Parent Category (if category has no parent choose Categories):");
		txtpnChooseParentCategory.setBackground(Color.DARK_GRAY);
		txtpnChooseParentCategory.setBounds(10, 0, 350, 34);
		frmEditorInChief.getContentPane().add(txtpnChooseParentCategory);
		
		
	}
}
