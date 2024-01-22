package view;

import view.View_HRD;
import view.View_Manager;
import view.View_Product;
import view.View_TransactionManagement;

import controller.EmployeeController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View_Login extends JFrame implements ActionListener, KeyListener {
	
	private JButton buttonViewHRD;
	private JButton buttonViewManager;
	private JButton buttonViewTransactionManagement;
	private JButton buttonViewProduct;
	
	
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JButton buttonLogin;

	public View_Login() {
		// JFrame
		setTitle("Login View");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel contentPane
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(2, 1, 10, 10));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
				
		// JPanel Buttons
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		// JButton HRD
		buttonViewHRD = new JButton("HRD");
		buttonViewHRD.addActionListener(this);
//		panelButtons.add(buttonViewHRD);
				
		// JButton Product
		buttonViewProduct = new JButton("Product");
		buttonViewProduct.addActionListener(this);
//		panelButtons.add(buttonViewProduct);
		
		// JButton Manager
		buttonViewManager = new JButton("Manager");
		buttonViewManager.addActionListener(this);
//		panelButtons.add(buttonViewManager);
		
		// JButton Transaction Management
		buttonViewTransactionManagement = new JButton("Transaction Management");
		buttonViewTransactionManagement.addActionListener(this);
//		panelButtons.add(buttonViewTransactionManagement);

		// JPanel Login
		JPanel panelLogin = new JPanel();
		panelLogin.setLayout(new FlowLayout());
		
		// JLabel Username
		JLabel labelUsername = new JLabel("Username: ");
		labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLogin.add(labelUsername);
		
		// JTextField Username
		fieldUsername = new JTextField();
		fieldUsername.setPreferredSize(new Dimension(100, 20));
		fieldUsername.addKeyListener(this);
		panelLogin.add(fieldUsername);
		
		// JLabel Password
		JLabel labelPassword = new JLabel("Password: ");
		labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLogin.add(labelPassword);
		
		// JTextField Password
		fieldPassword = new JPasswordField();
		fieldPassword.setPreferredSize(new Dimension(100, 20));
		fieldPassword.addKeyListener(this);
		panelLogin.add(fieldPassword);
		
		// JButton Login
		buttonLogin = new JButton("Login");
		buttonLogin.addActionListener(this);
		panelLogin.add(buttonLogin);
		

		add(panelButtons);
		
		add(panelLogin, BorderLayout.SOUTH);		

		setVisible(true);

		fieldUsername.requestFocusInWindow();
		
	}
	
	private void loginTransactionManagement(){
		View_Product viewTransactionManagement = new View_Product(1);
		viewTransactionManagement.setVisible(true);
		dispose();
		
	}
	
	private void loginProduct(){
		View_Product viewProduct = new View_Product(2);
		viewProduct.setVisible(true);
		dispose();
		
	}
	
	private void loginHRD(){
		View_HRD viewHRD = new View_HRD();
		viewHRD.setVisible(true);
		dispose();
		
	}
	
	private void loginManager(){
		View_Manager viewManager = new View_Manager();
		viewManager.setVisible(true);
		dispose();
		
	}
	
	private void loginEmployee() {
		String username = fieldUsername.getText();
		@SuppressWarnings("deprecation")
		String password = fieldPassword.getText();
				
//		System.out.println(EmployeeController.logEmployeeIn(username, password));
		
		switch(EmployeeController.logEmployeeIn(username, password)) {
			case 1: {
				loginTransactionManagement();
				break;
			}
			case 2: {
				loginProduct();
				break;
			}
			case 3: {
				loginHRD();
				break;
			}
			case 4: {
				loginManager();
				break;
			}
			
			case -1: {
				JOptionPane.showMessageDialog(null, "Invalid username or password.");
				break;
			}
			
			case -2: {
				JOptionPane.showMessageDialog(null, "Fields cannot be empty.");
				break;
			}
		}
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonViewHRD) {
			loginHRD();
		}
		else if (e.getSource() == buttonViewProduct) {
			loginProduct();
		}
		else if (e.getSource() == buttonViewManager) {
			loginManager();
		}
		else if (e.getSource() == buttonViewTransactionManagement) {
			loginTransactionManagement();
		}
//		else if (e.getSource() == button) {
//		
//		}
		
		else if (e.getSource() == buttonLogin){
			// TO-DO LOGIN STUFF
			loginEmployee();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			loginEmployee();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}