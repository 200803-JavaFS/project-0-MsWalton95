package com.revature.model;

public class Transaction {
	private int transID;
	private String accType;
	private double amount;
	private int accID;
	private long totalBalance;
	
	public Transaction() {
		this.transID = transID;
		this.accType = accType;
		this.amount = amount;
		this.accID = accID;
		this.totalBalance = totalBalance;
	}

	public Transaction(int transID, String accType, double amount, int accID, long totalBalance) {
		super();
		this.transID = transID;
		this.accType = accType;
		this.amount = amount;
		this.accID = accID;
		this.totalBalance = totalBalance;
	}

	@Override
	public String toString() {
		return "[Transaction ID: " + transID + "\t Account Type: " + accType + "\t Amount: " + amount + "\t Account ID: " + accID
				+ "\t Total Balance: " + totalBalance + "]";
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

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public long getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(long totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	
}
