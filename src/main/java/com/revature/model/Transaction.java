package com.revature.model;

import java.sql.Timestamp;

public class Transaction {
	private int transID;
	private String accType;
	private double amount;
	private int userID;
	private double totalBalance;
	private Timestamp timeStamp;

	public Transaction() {
		this.transID = 0;
		this.accType = null;
		this.amount = 0;
		this.userID = 0;
		this.totalBalance = 0;
		this.timeStamp = null;
	}

	public Transaction(int transID, String accType, double amount, int accID, double totalBalance, Timestamp timeStamp) {
		this.transID = transID;
		this.accType = accType;
		this.amount = amount;
		this.userID = accID;
		this.totalBalance = totalBalance;
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "[Transaction ID: " + transID + "\t Account Type: " + accType + "\t Amount: " + amount
				+ "\t Total Balance: " + totalBalance +  "\t Customer ID: " + userID + "\t Date: "+ timeStamp+"]";
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}	
	
	public Timestamp getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timeStamp = timestamp;
	}
	
}
