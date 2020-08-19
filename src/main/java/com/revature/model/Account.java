package com.revature.model;

public class Account {
	private int userID;
	private int accID;
	private String accName;
	private double balance;
	private String accType;
	private boolean isApproved;
	
	public Account() {
		accID = 0;
		userID = 0;
		accName = null;
		balance = 0;
		accType = null;
		isApproved = false;
	}
	
	public Account(String accName, String accType, boolean isApproved) {
		this.accName = accName;
		this.accType = accType;
		this.isApproved = isApproved;
	}

	public Account(int accID, int userID, String accName, double balance, String accType, boolean isApproved) {
		this.accID = accID;
		this.userID = userID;
		this.accName = accName;
		this.balance = balance;
		this.accType = accType;
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "[Customer ID: " + userID + "\t Account ID: " + accID + "\t Account Name: " + accName + "\t Balance: " + balance + "\t Account Type: "
				+ accType + "\t Approved: " + isApproved + "]";
	}

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAccName() {
		return accName;
	}

	public String setAccName(String accName) {
		return this.accName = accName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}


}
