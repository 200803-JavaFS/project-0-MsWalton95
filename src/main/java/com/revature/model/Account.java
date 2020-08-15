package com.revature.model;

public class Account {
	private int accID;
	private int accNumber;
	private String accName;
	private double balance;
	private String accType;
	private boolean isApproved;
	
	public Account() {
		accID = 0;
		accNumber = 0;
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

	public Account(int accID, int accNumber, String accName, double balance, String accType, boolean isApproved) {
		this.accID = accID;
		this.accNumber = accNumber;
		this.accName = accName;
		this.balance = balance;
		this.accType = accType;
		this.isApproved = isApproved;
	}
	
	

	@Override
	public String toString() {
		return "[Account ID: " + accID + "\t Account Number: " + accNumber + "\t Account Name: " + accName + "\t Balance:" + balance + "\t Account Type:"
				+ accType + "\t Approved: " + isApproved + "]";
	}

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
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
