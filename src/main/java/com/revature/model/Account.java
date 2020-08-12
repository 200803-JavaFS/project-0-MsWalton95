package com.revature.model;

public class Account {
	private int accNumber;
	private String accName;
	private double balance;
	private String accType;
	private boolean isApproved;
	
	public Account() {
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

	public Account(int accNumber, String accName, double balance, String accType, boolean isApproved) {
		this.accNumber = accNumber;
		this.accName = accName;
		this.balance = balance;
		this.accType = accType;
		this.isApproved = isApproved;
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

	public void setAccName(String accName) {
		this.accName = accName;
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
