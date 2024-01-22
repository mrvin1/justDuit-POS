package controller;

import java.time.LocalDate;
import java.util.Vector;

import model.Cart;
import model.Product;
import model.Transaction;
import model.TransactionItem;

public class TransactionController {
	
	public static Vector<Transaction> getAllTransactions() {
		return Transaction.getAllTransaction();
	}
	
	public static Vector<Transaction> getTodayTransaction() {
		LocalDate today = LocalDate.now();
		return Transaction.getTransactionReport(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
	}
	
	public static Vector<Transaction> viewTransactionReport(int month, int year) {
		return Transaction.getTransactionReport(0, month, year);
	}
	
	public static Vector<TransactionItem> getAllTransactionItem(int transactionId) {
		return TransactionItem.getTransactionItem(transactionId);
	}
	
	public static String pushTransaction(int employeeid,String paymentType) {
		Transaction i = new Transaction(0, null,employeeid, paymentType);
		boolean isSuccess = i.pushTransaction();
		if(isSuccess == false) {
			return "Insert Failed";
			}
			else {
				controller.CartController.setGrandTotal(0);
				return null;
			}
	}

	public TransactionController() {
		
	}

}
