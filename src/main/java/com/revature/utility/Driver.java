package com.revature.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.services.*;

public class Driver {
	CustomerService c = new CustomerService();
	EmployeeService e = new EmployeeService();
	AdminService a = new AdminService();
	UserService u = new UserService();
	
	Scanner sc = new Scanner(System.in);
	
	public void intro() {
		System.out.println(
				" _._._                       _._._ " + "\n" + 
		        "_|   |_                     _|   |_" + "\n" +
				"| ... |_._._._._._._._._._._| ... |" + "\n" +
				"| ||| |  o REVATURE BANK o  | ||| |" + "\n" +
				"| ''' |  '''    '''    '''  | ''' |" + "\n" +
				"|[-|-]| [-|-]  [-|-]  [-|-] |[-|-]|" + "\n" +
				"|     |---------------------|     |" + "\n" +
				"| ''' |  '''    '''    '''  | ''' |" + "\n" +
				"|[-|-]|  :::   .-'-.   :::  |[-|-]|" + "\n" +
				"|     | |~|~|  |_|_|  |~|~| |     |" + "\n" +
				"|_____|_|_|_|__|_|_|__|_|_|_|_____|" + "\n"
		);
		System.out.println("     Welcome to Revature Bank");
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		System.out.println("Choose your account \n");
		System.out.println("1. Client \n" + "2. Employee \n" + "3. Administration");
		System.out.println("\n" + "-----------------------------------" + "\n");
	}
		
	public void introAccount() {
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					c.login(); break;
				case 2: 
					e.login(); break;
				case 3: 
					a.login(); break;
				default: 
					System.out.println("Wrong input. Try again");
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}
	}
	
	private static final Logger log = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		
		Driver d = new Driver();
		d.intro();
		d.introAccount();
		
	}
}
