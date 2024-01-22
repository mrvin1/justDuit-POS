package view;

import controller.HRDController;
import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.TransactionController;
import model.Employee;
import model.Transaction;

public class View_HRD extends JFrame implements ActionListener{
	
	private JTable table;
	private DefaultTableModel utm;
	private JButton add_user;
	private JButton edit_user;
	private JButton delete_user;
	private JFrame Add;
	private JFrame Edit;
	private JFrame Delete;
	private JButton AddBtn;
	private JButton EditBtn;
	private JButton DelBtn;
	
	JSpinner IDField;
	JSpinner RoleField;
	JTextField NameField;
	JTextField UsernameField;
	JSpinner SalaryField;		
	JTextField PasswordField;
	
	public View_HRD() {
		initLayout();
		setUpDataModel();
		refreshData();
	}
	
	private void initLayout() {
		setSize(800, 600);
		setTitle("Employee List");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(2, 1, 10, 10));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		JPanel panelCtrl = new JPanel();
		panelCtrl.setLayout(new GridLayout(1, 2, 10, 10));
		
		// JPanel Buttons
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
				
		// JButton Add
		add_user = new JButton("Add User");
		add_user.addActionListener(this);
		panelButtons.add(add_user);
		
		// JButton Edit
		edit_user = new JButton("Edit User");
		edit_user.addActionListener(this);
		panelButtons.add(edit_user);
		
		// JButton Delete
		delete_user = new JButton("Fire user");
		delete_user.addActionListener(this);
		panelButtons.add(delete_user);
		
		panelCtrl.add(panelButtons);
		
		add(panelCtrl);
		
		contentPane.setVisible(true);
	}
	
	private void setUpDataModel() {
		utm = new DefaultTableModel(
				new String[] { "id", "role_id", "name","username", "salary", "status"},
				0
			);
			table.setModel(utm);
	}
	
	private void refreshData() {
		utm.setRowCount(0);
		Vector<Employee> employees = EmployeeController.getAllEmployee();
		
		for (Employee i : employees) {
			utm.addRow(new Object[] {
					i.getId(),
					i.getRoleID(),
					i.getName(),
					i.getUsername(),
					i.getSalary(),
					i.getStatus(),
					i.getPassword()
			});
		}
	}
	
private void AddUser() {
		
		Add = new JFrame();
		
		Add.setTitle("Add User");
		Add.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Add.setBounds(200, 200, 400, 300);
		
		Container Cont = Add.getContentPane();
		Cont.setLayout(null);
		
		JLabel Title = new JLabel("Add User");
		Title.setBounds(120, 5, 200, 30);
		
		JLabel RoleLabel = new JLabel("Role ID: ");
		RoleLabel.setBounds(20, 30, 200, 30);
		
		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setBounds(20, 60, 200, 30);
		
		JLabel UsernameLabel = new JLabel("Username: ");
		UsernameLabel.setBounds(20, 90, 200, 30);
		
		JLabel SalaryLabel = new JLabel("salary: ");
		SalaryLabel.setBounds(20, 120, 200, 30);
		
		JLabel PasswordLabel = new JLabel("Password: ");
		PasswordLabel.setBounds(20, 150, 200, 30);
		
		RoleField = new JSpinner();
		RoleField.setBounds(100, 30, 200, 30);
		
		NameField = new JTextField();
		NameField.setBounds(100, 60, 200, 30);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(100, 90, 200, 30);
		
		SalaryField = new JSpinner();
		SalaryField.setBounds(100, 120, 200, 30);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(100, 150, 200, 30);

		AddBtn = new JButton("Add");
		AddBtn.addActionListener(this);
		AddBtn.setBounds(150, 200, 100, 30);
		
		Cont.add(Title);
		Cont.add(RoleLabel);
		Cont.add(NameLabel);
		Cont.add(UsernameLabel);
		Cont.add(SalaryLabel);
		Cont.add(PasswordLabel);
		Cont.add(RoleField);
		Cont.add(NameField);
		Cont.add(UsernameField);
		Cont.add(SalaryField);
		Cont.add(AddBtn);
		Cont.add(PasswordField);
	}

private void EditUser() {
	
	Edit = new JFrame();
	
	Edit.setTitle("Edit User");
	Edit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	Edit.setBounds(200, 200, 400, 300);
	
	Container Cont = Edit.getContentPane();
	Cont.setLayout(null);
	
	JLabel Title = new JLabel("Edit User");
	Title.setBounds(120, 5, 200, 30);
	
	JLabel IDLabel = new JLabel("ID: ");
	IDLabel.setBounds(20, 30, 200, 30);
	
	JLabel RoleLabel = new JLabel("Role ID: ");
	RoleLabel.setBounds(20, 60, 200, 30);
	
	JLabel NameLabel = new JLabel("Name: ");
	NameLabel.setBounds(20, 90, 200, 30);
	
	JLabel UsernameLabel = new JLabel("Username: ");
	UsernameLabel.setBounds(20, 120, 200, 30);
	
	JLabel SalaryLabel = new JLabel("salary: ");
	SalaryLabel.setBounds(20, 150, 200, 30);
	
	JLabel PasswordLabel = new JLabel("Password: ");
	PasswordLabel.setBounds(20, 180, 200, 30);
	
	IDField = new JSpinner();
	IDField.setBounds(100, 30, 200, 30);
	
	RoleField = new JSpinner();
	RoleField.setBounds(100, 60, 200, 30);
	
	NameField = new JTextField();
	NameField.setBounds(100, 90, 200, 30);
	
	UsernameField = new JTextField();
	UsernameField.setBounds(100, 120, 200, 30);
	
	SalaryField = new JSpinner();
	SalaryField.setBounds(100, 150, 200, 30);
	
	PasswordField = new JPasswordField();
	PasswordField.setBounds(100, 180, 200, 30);

	EditBtn = new JButton("Update");
	EditBtn.addActionListener(this);
	EditBtn.setBounds(150, 225, 100, 30);
	
	Cont.add(Title);
	Cont.add(IDLabel);
	Cont.add(RoleLabel);
	Cont.add(NameLabel);
	Cont.add(UsernameLabel);
	Cont.add(SalaryLabel);
	Cont.add(PasswordLabel);
	Cont.add(IDField);
	Cont.add(RoleField);
	Cont.add(NameField);
	Cont.add(UsernameField);
	Cont.add(SalaryField);
	Cont.add(PasswordField);
	Cont.add(EditBtn);
}

private void DeleteUser() {
	
	Delete = new JFrame();
	
	Delete.setTitle("Fire User");
	Delete.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	Delete.setBounds(200, 200, 400, 300);
	
	Container Cont = Delete.getContentPane();
	Cont.setLayout(null);
	
	JLabel Title = new JLabel("Fire User");
	Title.setBounds(120, 5, 200, 30);
	
	JLabel IDLabel = new JLabel("ID: ");
	IDLabel.setBounds(20, 30, 200, 30);
	
	IDField = new JSpinner();
	IDField.setBounds(100, 30, 200, 30);

	DelBtn = new JButton("Fire");
	DelBtn.addActionListener(this);
	DelBtn.setBounds(150, 75, 100, 30);
	
	Cont.add(Title);
	Cont.add(IDLabel);
	Cont.add(IDField);
	Cont.add(DelBtn);
	
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add_user) {
			AddUser();
			Add.setVisible(true);
		} else if (e.getSource() == edit_user) {
			EditUser();
			Edit.setVisible(true);
		} else if (e.getSource() == delete_user) {
			DeleteUser();
			Delete.setVisible(true);
		}
		if(e.getSource() == AddBtn) {
			HRDController.addUser(0, (int) RoleField.getValue(), NameField.getText(), UsernameField.getText(), (int) SalaryField.getValue(), "Active", PasswordField.getText());
			refreshData();
			Add.setVisible(false);
		} else if (e.getSource() == EditBtn) {
			HRDController.editUser((int) IDField.getValue(), (int) RoleField.getValue(), NameField.getText(), UsernameField.getText(), (int) SalaryField.getValue(), "Active", PasswordField.getText());
			refreshData();
			Edit.setVisible(false);
		} else if (e.getSource() ==  DelBtn) {
			HRDController.deleteUser((int) IDField.getValue());
			refreshData();
			Delete.setVisible(false);
		}
	}
}
