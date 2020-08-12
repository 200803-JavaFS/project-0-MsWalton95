package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Employee;

public class EmployeeService extends UserService {
	Employee e = new Employee();
	
	public void login() {
		super.login();
	}
	
	public void signin() {
		super.signin();
		System.out.println("Welcome " + e.getUserName() + "!");
		option();
	}
	
	public void option() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Customer Info \n" + "2. Customer Account \n" + 
							"3. Customer Transactions \n" + "4. Exit" );
		System.out.println("\n" + "-----------------------------------" + "\n");
			
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					viewCustomerInfoByID(); break;
				case 2:
					viewCustomerAccByID(); break;
				case 3:
					viewCustomerTransByID(); break;
				case 4:
					logout(); break;
				default: 
					System.out.println("Wrong input. Try again"); 
					retry();
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public List<Customer> viewCustomerInfoByID(){
		return null;
	}
	
	public List<Account> viewCustomerAccByID(){
		return null;
	}
	
	public List<Account>viewCustomerTransByID(){
		return null;
	}
	
	public boolean openApplication() {
		return false;
	}
}
