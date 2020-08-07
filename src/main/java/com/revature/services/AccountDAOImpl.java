package com.revature.services;

public class AccountDAOImpl implements AccountDAO{
	
	int balance = 0;
	
	public int withdraw(int money) {
		if(balance < money) {
			System.out.println("Cannot withdrawn more than balance");
		}else if(money <= 0) {
			System.out.println("Cannot insert a negative value");
		}else {
			balance -= money; 
			System.out.println("Withdraw: " + money);
			System.out.println("Current balance: " + balance);
		}
		return balance;
	}
	
	public int deposit(int money) {
		if (money <= 0) {
			System.out.println("Cannot insert a negative value");
		} else {
			balance += money;
			System.out.println("Deposit: " + money);
			System.out.println("Current balance: " + balance);
		}
		return balance;
	}
}
