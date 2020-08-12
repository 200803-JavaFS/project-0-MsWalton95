package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {		

	Scanner sc = new Scanner(System.in);
	
	public void login() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Sign in \n" + "2. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					signin(); break;
				case 2:
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
	
	public void logout() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("Thank You For Using Revature Bank!");
		System.out.println("\n" + "-----------------------------------" + "\n");
	}
	
	public void signin() {
		System.out.println("\n" + "-----------------------------------" + "\n");
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
	
	public void retry() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Continue \n" + "2. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
	}
}
