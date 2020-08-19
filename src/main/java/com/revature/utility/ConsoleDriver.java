package com.revature.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.AdminDAO;
import com.revature.dao.CustomerDAO;
import com.revature.dao.EmployeeDAO;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.revature.services.*;

public class ConsoleDriver {
	CustomerDAO c = new CustomerService();
	EmployeeDAO e = new EmployeeService();
	AdminDAO a = new AdminService();
	
	Scanner sc = new Scanner(System.in);
	Thread t1=new Thread(){
		
	    public void run(){  
	    	try{  
				 System.out.println("\n ----------------------------------- \n");
				 System.out.println(" Loading Home Page...");
				 Thread.sleep(1000); 
				 begin();
				 
	        }catch(InterruptedException e){
	       	 System.out.println(e); 
	       	 }
	    }  
	 };  
	 
	public void begin() {
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
		System.out.println("\n ----------------------------------- \n");		
		System.out.println(" CHOOSE YOUR ACCOUNT \n");
		System.out.println(" 1. Client \n 2. Employee \n 3. Administration");
		System.out.println("\n ----------------------------------- \n");
		chooseAccount();
	}
		
	public void chooseAccount() {
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					c.signin();
					break;
				case 2: 
					e.signin(); break;
				case 3: 
					a.signin(); break;
				default: 
					System.out.println(" Wrong input. Try again");
					t1.run();
			}
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input. Try Again");
			sc.next();
			t1.run();
			
		}
	}
}
