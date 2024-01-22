package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import connect.Connect;
import model.Cart;
import model.Transaction;
import model.TransactionItem;

public class TransactionItemController {
	private int transactionid;
	private int productID;
	private int quantity;
	
	public TransactionItemController(int transactionid, int productid,int qty) {
		this.transactionid=transactionid;
		this.productID=productid;
		this.quantity=qty;
	}
	
	public static String pushTransactionItem() {
		TransactionItem i = new TransactionItem(0,0,0);
		boolean isSuccess = i.push();
		if(isSuccess == false) {
			return "Insert Failed";
			}
			else {
				return null;
			}
	}
}
