package view;

import model.Cart;
import model.Employee;
import model.Product;
import model.Transaction;
import model.TransactionItem;
import controller.CartController;
import controller.ProductM_Controller;
import controller.TransactionController;
import controller.TransactionItemController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class View_TransactionManagement extends JFrame implements ActionListener{
	private JTable table;
	private DefaultTableModel dtm;
	private JSpinner spinnerId;
	private JSpinner spinnerQuantity;
	private JButton buttonAdd;
	private JButton buttonRemove;
	private JButton buttonCheckOut;
	private JButton buttonViewHistory;
	private JLabel totalprice=new JLabel(); 
	
	private JFrame historyFrame;
	private JTable historyTable;
	private DefaultTableModel historyDtm;
	
	private JFrame productFrame;
	private JTable productTable;
	private DefaultTableModel productDtm;

	
	public View_TransactionManagement() {
		initLayout();
		setUpDataModel();
		refreshData();
		prepHistoryFrame();
		prepProductFrame();
	}
	
	private void initLayout() {
		// JFrame
		setTitle("Transaction Management");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel ContentPane
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(2, 1, 10, 10));
		setContentPane(contentPane);

		// JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		
		// JTable
		table = new JTable();
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		// JPanel CUD
		JPanel panelCud = new JPanel();
		panelCud.setLayout(new GridLayout(1, 2, 10, 10));
		
		// JPanel Fields
		JPanel panelFields = new JPanel();
		panelFields.setLayout(new GridLayout(3, 2));
		
		// JLabel Id
		JLabel labelId = new JLabel("Product Id: ");
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelId);
		
		// JLabel Quantity
		JLabel labelQuantity = new JLabel("Quantity: ");
		labelQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelQuantity);
		
		// JSpinner ID
		spinnerId = new JSpinner();
		panelFields.add(spinnerId);
		
		// JSpinner Quantity
		spinnerQuantity = new JSpinner();
		panelFields.add(spinnerQuantity);
		
		panelCud.add(panelFields);
		
		// JLabel price
		JLabel labeltotalprice = new JLabel("Total Price: ");
		labeltotalprice.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labeltotalprice);
		
		totalprice.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(totalprice);

		// JPanel Buttons
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		// JButton Add
		buttonAdd = new JButton("Add item");
		buttonAdd.addActionListener(this);
		panelButtons.add(buttonAdd);
		
		// JButton Remove
		buttonRemove = new JButton("Remove item");
		buttonRemove.addActionListener(this);
		panelButtons.add(buttonRemove);
		
		// JButton checkout
		buttonCheckOut = new JButton("Check Out");
		buttonCheckOut.addActionListener(this);
		panelButtons.add(buttonCheckOut);
		
		// JButton history
		buttonViewHistory = new JButton("View History");
		buttonViewHistory.addActionListener(this);
		panelButtons.add(buttonViewHistory);
		
		panelCud.add(panelButtons);
		add(panelCud);
		contentPane.setVisible(true);
	}
	
	private void setUpDataModel() {
		dtm = new DefaultTableModel(
			new String[] {"Product ID","Quantity"},
			0
		);
		table.setModel(dtm);
	}
	
	private void refreshData() {
		dtm.setRowCount(0);
		Vector<Cart> cart = Cart.view();
		
		for(Cart i:cart) {
			dtm.addRow(new Object[] {
					i.getProductid(),
					i.getQty()
			});
		}
		totalprice.setText(String.valueOf(CartController.getGrandTotal()));
	}
	
	private void prepHistoryFrame() {
		
		historyFrame = new JFrame();
		
		historyFrame.setTitle("Transaction History View");
		historyFrame.setSize(600, 400);
		historyFrame.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// JPanel
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(1, 1, 10, 10));
		historyFrame.setContentPane(contentPane);
		
		// JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		
		// JTable
		historyTable = new JTable();
		historyTable.setDefaultEditor(Object.class, null);
		
		historyTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int row = historyTable.rowAtPoint(evt.getPoint());
				int transactionid = (int) historyTable.getValueAt(row, 0);
				viewAllTransactionItem(transactionid);
			}
		});
		
		// TableModel_TransactionItem
		historyDtm = new DefaultTableModel(
			new String[] { "id", "purchaseDate", "employeeId", "paymentType" },
			0
		);
		historyTable.setModel(historyDtm);
				
		scrollPane.setViewportView(historyTable);
		historyFrame.add(scrollPane);
		
	}
	
	private void viewHistory() {
		
		Vector<Transaction> transactions = TransactionController.getTodayTransaction();

		// Get table contents
		historyDtm.setRowCount(0);
		
		for (Transaction i : transactions) {
			historyDtm.addRow(new Object[] {
				i.getId(),
				i.getPurchaseDate(),
				i.getEmployeeId(),
				i.getPaymentType()
			});
		}
		
		historyFrame.setVisible(true);	
		
		return;
		
	}
	
	private void prepProductFrame() {
		
		productFrame = new JFrame();
		
		productFrame.setTitle("TransactionItem View");
		productFrame.setSize(600, 400);
		productFrame.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		
		// JTable
		productTable = new JTable();
		productTable.setDefaultEditor(Object.class, null);
		
		// JPanel
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(1, 1, 10, 10));
		productFrame.setContentPane(contentPane);
		
		// TableModel_TransactionItem
		productDtm = new DefaultTableModel(
			new String[] { "transaction_id", "product_id", "quantity" },
			0
		);
		productTable.setModel(productDtm);
				
		scrollPane.setViewportView(productTable);
		productFrame.add(scrollPane);
		
	}
	
	private void viewAllTransactionItem(int transactionId) {
		Vector<TransactionItem> transactionitems = TransactionController.getAllTransactionItem(transactionId);
				
		// Get table contents
		productDtm.setRowCount(0);
		
		for (TransactionItem i : transactionitems) {
			productDtm.addRow(new Object[] {
				i.getTransactionid(),
				i.getProductid(),
				i.getQuantity()
			});
		}
				
		productFrame.setVisible(true);		
		
		return;
	}
	
	private void add() {
		int productid = (int)spinnerId.getValue();
		int qty = (int)spinnerQuantity.getValue();
		
		String error = CartController.addItem(productid, qty);
		if(error == null) {
			refreshData();
		}else {
			JOptionPane.showMessageDialog(this, error);
		}
	}
	
	private void remove() {
		int productid = (int)spinnerId.getValue();
		String error =  CartController.removeItem(productid, 0);
		if(error ==null) {
			refreshData();
		}else {
			JOptionPane.showMessageDialog(this, error);
		}
}
	
	private void checkout() {
		Object[]opt= {"Cash", "Credit Card","Cancel"};
		String payMethod = null;
		int opsi=JOptionPane.showOptionDialog(this, "Choose the payment method","Payment Method",
				JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,
				opt, opt[0]);
		if(opsi==0) {
			//cash
			payMethod = "CASH";
		}
		else if(opsi==1){
			//CC
			payMethod = "CREDIT CARD";
		}
		else if(opsi==2)
		{
			return;
		}
		String error = TransactionController.pushTransaction(Employee.idNow,payMethod);
		error=TransactionItemController.pushTransactionItem();
		error=ProductM_Controller.updateStock();
		error= CartController.deleteAll();
		refreshData();	
		JOptionPane.showMessageDialog(this, error);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAdd) {
			add();
		} else if (e.getSource() == buttonRemove) {
			remove();
		} else if (e.getSource() == buttonCheckOut) {
			checkout();
		}
		else if(e.getSource()==buttonViewHistory) {
			viewHistory();
		}
	}
}
