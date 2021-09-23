package athirinaProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicerInterface {

	public JFrame frmPublicer;

	

	/**
	 * Create the application.
	 */
	public PublicerInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPublicer = new JFrame();
		frmPublicer.setTitle("Publicer");
		frmPublicer.getContentPane().setBackground(Color.DARK_GRAY);
		frmPublicer.setBounds(100, 100, 450, 300);
		frmPublicer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPublicer.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Edit Newspaper Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PublicerNewsData window = new PublicerNewsData();
							window.frmPublicer.setVisible(true);
							frmPublicer.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(127, 11, 174, 30);
		frmPublicer.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Insert Prints For Issue");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PublicerIssuePrints window = new PublicerIssuePrints();
							window.frmPublicer.setVisible(true);
							frmPublicer.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(127, 77, 174, 30);
		frmPublicer.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Define Chief Editor");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PublicerDefineChief window = new PublicerDefineChief();
							window.frmPublicer.setVisible(true);
							frmPublicer.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(127, 149, 174, 30);
		frmPublicer.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("See Issue Sales");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PublicerSeeSales window = new PublicerSeeSales();
							window.frmPublicer.setVisible(true);
							frmPublicer.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setBounds(127, 220, 174, 30);
		frmPublicer.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login window = new login();
				window.frame.setVisible(true);	
				frmPublicer.dispose();
			}
		});
		btnNewButton_4.setBackground(Color.DARK_GRAY);
		btnNewButton_4.setBounds(0, 227, 89, 23);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBorderPainted(false);
		frmPublicer.getContentPane().add(btnNewButton_4);
		
	}
}
