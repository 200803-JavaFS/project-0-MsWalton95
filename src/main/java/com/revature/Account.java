package com.revature;

public class Account {
	
	int balance = 50;
	//Once the account is open, customers should be able to withdraw, deposit, and transfer funds between accounts
		//    All basic validation should be done, such as trying to input negative amounts, overdrawing from accounts etc.
		
		//View Account - current checking and saving
		//View Transaction History - show 10 transactions - prev/next
		
		
		//Withdraw
	public void withdraw(int rm) {
		if(balance < rm) {
			System.out.println("Cannot withdrawn more than balance");
		}else if(rm <= 0) {
			System.out.println("Cannot insert a negative value");
		}else {
			balance-=rm; 
			System.out.println("Current balance: " + balance);
		}
	}
		//Deposit
	public void deposit(int add) {
		if (add <= 0) {
			System.out.println("Cannot insert a negative value");
		} else {
			balance+=add;
			System.out.println("Current balance: " + balance);
		}
	}
		
		//Transfer between spend,reserve, and growth

}
