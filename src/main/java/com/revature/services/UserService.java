package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.utility.ConsoleDriver;


public class UserService {		
	
	Scanner sc = new Scanner(System.in);
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void signin() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Log in \t 2. Exit");
		System.out.println("\n ----------------------------------- \n");
		
		int choice = sc.nextInt();
		option(choice);
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login() {
		System.out.println("\n ----------------------------------- \n");
		try {
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			String password = sc.next();
			
			System.out.println();
	
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void option(int choice) {
		switch(choice) {
			case 1: login();break;
			case 2: logout();break;
			default: System.out.println("Invalid Input");
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void homePage() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Customer Info \n 2. Customer Account \n 3. Customer Transactions \n 4. Log Out" );
		System.out.println("\n ----------------------------------- \n");
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void logout() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" Thank You For Using Revature Bank!");
		System.out.println("\n ----------------------------------- \n");
		System.exit(0);
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

}
