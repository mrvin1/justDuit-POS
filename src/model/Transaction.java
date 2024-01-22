package model;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import connect.Connect;

public class Transaction {
	
	private Integer id;
	private Date purchaseDate;
	private Integer employeeId;
	private String paymentType; 
	
	private Connect con;

	public Transaction(int id, Date purchaseDate, int employeeId, String paymentType) {
		this.id = id;
		this.purchaseDate=purchaseDate;
		this.employeeId = employeeId;
		this.paymentType = paymentType;
	}

	public static Vector<Transaction> getAllTransaction(){
		Vector<Transaction> results = new Vector<Transaction>();
		
		try {
			Statement st = Connect.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transaction");
			
			while(rs.next()) {
				results.add(new Transaction(
							rs.getInt("id"),
							rs.getDate("purchaseDate"),
							rs.getInt("employeeId"),
							rs.getString("paymentType")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
		
	public static Vector<Transaction> getTransactionReport(int date, int month, int year) {
		Vector<Transaction> results = new Vector<Transaction>();
		String query;
		if(date == 0) {
			query = "SELECT * FROM transaction WHERE MONTH(purchaseDate) = ? AND YEAR(purchaseDate) = ? ";
		} else {
			query = "SELECT * FROM transaction WHERE MONTH(purchaseDate) = ? AND YEAR(purchaseDate) = ? AND DAY(purchaseDate) = ? ";			
		}
		try {
			PreparedStatement ps = Connect.connect().prepareStatement(query);
			ps.setInt(1, month);
			ps.setInt(2, year);
			if(date != 0) {
				ps.setInt(3, date);
			}
			
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				results.add(new Transaction(
						rs.getInt("id"),
						rs.getDate("purchaseDate"),
						rs.getInt("employeeId"),
						rs.getString("paymentType")
					));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public boolean pushTransaction() {
		try {
			PreparedStatement ps = Connect.connect().prepareStatement("INSERT INTO transaction values(?,curdate(),?,?)");
			ps.setInt(1, 0);
			ps.setInt(2, employeeId);
			ps.setString(3, paymentType);
			return (ps.executeUpdate() == 1);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Connect getCon() {
		return con;
	}

	public void setCon(Connect con) {
		this.con = con;
	}
}
