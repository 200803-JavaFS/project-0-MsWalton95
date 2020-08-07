package com.revature.model;

public class Account {
	int accountNumber;
	String accountName;
	int balance;
	String type;
	
	public Account() {
		accountNumber = 0;
		accountName = null;
		balance = 0;
		type = null;
	}

	public Account(int accountNumber, String accountName, int balance, String type) {
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.type = type;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
