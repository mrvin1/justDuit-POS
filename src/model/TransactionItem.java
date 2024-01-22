package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import connect.Connect;

public class TransactionItem {
	private int transactionid;
	private int productid;
	private int quantity;
	
	public TransactionItem(int transactionid, int productid, int quantity) {
		this.transactionid=transactionid;
		this.productid=productid;
		this.quantity=quantity;
	}

	public boolean push(){
		try {
			Statement st = Connect.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cartitem");
			while(rs.next()) {
				int productidtemp;
				int qtytemp;
				productidtemp=rs.getInt("productid");
				qtytemp=rs.getInt("quantity");
				PreparedStatement ps = Connect.connect().prepareStatement("insert into transactionitem values((SELECT MAX(ID) FROM transaction),?,?)");
				ps.setInt(1,productidtemp);
				ps.setInt(2, qtytemp);
				ps.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
 	public static Vector<TransactionItem> getTransactionItem(int transactionId) {
		Vector<TransactionItem> results = new Vector<TransactionItem>();
		String query = "SELECT * FROM TransactionItem WHERE TransactionId = ? ";	
		
		try {
			PreparedStatement ps = Connect.connect().prepareStatement(query);
			ps.setInt(1, transactionId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				results.add(new TransactionItem(
						rs.getInt("transactionID"),
						rs.getInt("productID"),
						rs.getInt("quantity")
					));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
