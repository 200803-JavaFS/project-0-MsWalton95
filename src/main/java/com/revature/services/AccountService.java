package com.revature.services;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import com.revature.model.Account;

public class AccountService extends UserService {
	double balance = 100.00;
	
	Account a = new Account();
	
	Scanner sc = new Scanner(System.in);
	
	public void retry() {
		super.retry();
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1: 
			withdraw();break;
		case 2: 
			option2(); break;
		default: 
			System.out.println("Wrong input. Try again"); 
			retry();
		}
	}
	
	public void option() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Manage Accounts \n" + "2. Update Personal Information \n" + "3. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1: 
			option2();break;
		case 2: 
			update(); break;
		case 3: 
			logout(); break;
		default: 
			System.out.println("Wrong input. Try again"); 
			option();
		}
	}
	
	public void option2() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Withdraw \n" + "2. Deposit \n" + "3. Transfer \n" + "4.Add Account" + "5. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1: 
			withdraw();break;
		case 2: 
			deposit(); break;
		case 3: 
			transfer(); break;
		case 4:
			createAccount();break;
		case 5:
			option();break;
		default: 
			System.out.println("Wrong input. Try again"); 
			option2();
		}
	}
	
//	public void option3() {
//		System.out.println("\n" + "-----------------------------------" + "\n");
//		System.out.println("1. Checkings \n" + "2. Savings \n" + "3. Growth \n" + "4. Exit");
//		System.out.println("\n" + "-----------------------------------" + "\n");
//		
//		int choice = sc.nextInt();
//		
//		switch(choice) {
//			case 1: 
//				withdraw();break;
//			case 2: 
//				deposit(); break;
//			case 3: 
//				transfer(); break;
//			default: 
//				System.out.println("Wrong input. Try again"); 
//				option2();
//		}
//	}
	
	public List<Account> viewAccount() {
		List<Account> accounts = new ArrayList<Account>();
		//number, name, balance, type,approve
		accounts.add(new Account(101, "default",550.00,"Checking",true));
		accounts.add(new Account(102,"summer vacation",50.0,"Saving",true));
		
		
		System.out.println("\n" + "-----------------------------------" + "\n");
		for(Account i : accounts) {
		System.out.println("Account Number: " + i.getAccNumber() + 
				"\t Account Name: " + i.getAccName() + 
				"\t Balance: " + i.getBalance() + 
				"\t Type: " + i.getAccType() +
				"\t Approved: " + i.isApproved());
		}
		option();
		return accounts;	
	}
	
	public boolean createAccount() {
		try {
			System.out.print("Account Name: ");
			String accName = sc.next();
			
			System.out.print("1. Checking \t 2.Saving");
			String accType = sc.next();
			
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void update() {
		
	}
	
	
	public void withdraw() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Withdraw Amount: ");
		
		try {
			int money = sc.nextInt();
			
			if(money <= 0) {
				System.out.println("Cannot withdraw negative value");
				retry();
				
			}else if(money > balance) {
				System.out.println("Cannot withdraw more than balance");
				retry();
			}else {
				balance-= money;
				System.out.println("Withdraw: " + money);
				System.out.println("Current Balance: " + balance);
				viewAccount();
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deposit() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Deposit amount: ");
		try {
			int money = sc.nextInt();
			
			if(money <= 0) {
				System.out.println("Cannot deposit negative value");
				retry();
			}else {
				balance+= money;
				System.out.println("Deposit: " + money + "\n Current Balance: " + balance);
				viewAccount();
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void transfer() {
		
		
	}
}
