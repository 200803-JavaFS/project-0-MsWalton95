package com.revature.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.UserLogin;

public class UserLoginDAOImpl implements UserLoginDAO {

	UserLogin ul = new UserLogin();
	Customer cu = new Customer();
	Account ac = new Account();
	Scanner sc = new Scanner(System.in);
	@Override
	public boolean createAccount(UserLogin user, Customer customer, Account acccount) {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("Please fill in your information");
		System.out.println("Press '0' to quit");
		System.out.println();
		try {
		System.out.print("First Name:");
		String firstName = sc.next();
		
		System.out.print("Last Name:");
		String lastName = sc.next();
		
		System.out.print("Account Number:");
		int accNumber = sc.nextInt();
		
		System.out.print("Create Username:");
		String username = sc.next();
		
		System.out.print("Create Password:");
		String password = sc.next();
		
				
		ArrayList<String> as = new ArrayList<String>();
		as.add(firstName);
		as.add(lastName);
		as.add(username);
		as.add(password);
		
		String x= "0";
		
		for(String i : as) {
			if(i.equals(x))
			{
				logOut();
			}else {
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			
			acccount.setAccountNumber(accNumber);
			
			user.setUsername(username);
			user.setPassword(password);
			}
		}
		
		System.out.println("New Account Created! Welcome " + customer.getFirstName()+"!");
		
		}catch(InputMismatchException e) {
			System.out.println("Incorrect input. Try Again");
			createAccount(ul,cu,ac);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
		
		
		
	}

	@Override
	public boolean logIn(UserLogin user) {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("Please enter your username and password");
		System.out.println("Press '0' to quit");
		System.out.println();
		
		System.out.print("Username:");
		String username = sc.next();
		
		System.out.print("Password:");
		String password = sc.next();
		
		user.setUsername(username);
		user.setPassword(password);
		
		System.out.println();
		System.out.println("Welcome!");
		
		return true;
	}

	@Override
	public void logOut() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("Thank you for visiting Revature Bank!");
		sc.close();
		
	}
	
	public void loginOption() {
		System.out.println(
				"1. Sign in" + "\n" + 
				"2. Create Account" + "\n" + 
				"3. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt(); 
		
			switch(choice) {
			case 1:
				logIn(ul);
				System.out.println("\n" + "-----------------------------------" + "\n");
				break;
			case 2:
				createAccount(ul, cu, ac);
				System.out.println("\n" + "-----------------------------------" + "\n");
				break;
			case 3:
				logOut();
				System.out.println("\n" + "-----------------------------------" + "\n");
				break;
			default:
				System.out.println("Please choose one of the options");
				System.out.println("\n" + "-----------------------------------" + "\n");
				loginOption();
			}
	}
	
	public void loginTitle() {
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
	}

}
