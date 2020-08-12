package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.Customer;
import com.revature.utility.Driver;

public class CustomerService extends UserService{
	AccountService acc = new AccountService();
	Customer c = new Customer();
	Scanner sc = new Scanner(System.in);
	
	public void login() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Sign in \n" + "2. Create Account \n" + "3. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		try {
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				signin(); break;
			case 2: 
				create(); break;
			case 3:
				logout(); break;
			default: 
				System.out.println("Wrong input. Try again"); 
				login();
		}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void signin() {
		super.signin();
		System.out.println("Welcome" + c.getFirstName() + "!");
		acc.viewAccount();
	}
	
	public void create() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		try {
			System.out.print("First Name: ");
			String firstname = sc.next();
			
			System.out.print("Last Name: ");
			String lastname = sc.next();
			
			System.out.print("Email: ");
			String email = sc.next();
			
			System.out.print("Phone Number: ");
			int number = sc.nextInt();
			
			System.out.print("Create Username: ");
			String username = sc.next();
			
			System.out.print("Create Password: ");
			String password = sc.next();
			
			System.out.print("Welcome " + firstname + " !");
			
			acc.createAccount();
		
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
