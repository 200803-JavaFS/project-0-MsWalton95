package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Customer;
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class CustomerService extends UserService{
	AccountService as = new AccountService();
	Customer c = new Customer();
	Scanner sc = new Scanner(System.in);
	
	private static final Logger log = LogManager.getLogger(Driver.class);

	Statement stmt;
	PreparedStatement psmt;
	
	
/*---------------------------------------------------------------------------------------------------------*/	
/*UPDATE, INSERT															*/
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void updateCustomer(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" 1. Email \n 2. Phone Number \n 3. Password \n 4. Exit");
			System.out.println("\n ----------------------------------- \n");
			
			int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				String sql1 ="UPDATE customer SET email=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql1);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Email Address: ");
				
				String email = sc.next();
				psmt.setString(1, email);
				
				log.info("Email Updated");
				break;
			case 2: //FIX INTEGER
				String sql2 ="UPDATE customer SET phone_number=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql2);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Phone Number: ");
				
				long phone = sc.nextLong();
				psmt.setLong(1, phone);
				
				log.info("Phone Number Updated");
				break;
			case 3: 
				String sql3 ="UPDATE customer SET pass_word=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql3);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update password: ");
				
				String password = sc.next();
				psmt.setString(1, password);
				
				log.info("Password Updated");
				break;
			case 4: 
				homePage();
				break;
			default: 
				System.out.println("Invalid Input");
			}
			
			psmt.setInt(2, userID);

			psmt.execute();
			homePage();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			System.out.println("Invalid Input");
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public boolean registerLogin() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql="UPDATE customer SET user_name=?, pass_word=? WHERE customer_id=?;";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print("Create your new username: ");
			String username = sc.next();
			psmt.setString(1, username);
			
			System.out.print("Create your new password: ");
			String password = sc.next();
			psmt.setString(2, password);
			
			System.out.print("Enter your existing account: ");
			int userID = sc.nextInt();
			psmt.setInt(3, userID);
			
			psmt.execute();
			System.out.println();
			log.info("New login account created");
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public boolean createAccount() {
		try(Connection conn = ConnectionDAO.connect()) {
			String sql ="INSERT INTO customer (user_name, pass_word, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?, ?);";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print("Create your new username: ");
			String username = sc.next();
			psmt.setString(1, username);
			
			System.out.print("Create your new password: ");
			String password = sc.next();
			psmt.setString(2, password);
			
			System.out.print("Enter your first name: ");
			String firstName = sc.next();
			psmt.setString(3, firstName);
			
			System.out.print("Enter your last name: ");
			String lastName = sc.next();
			psmt.setString(4, lastName);
			
			System.out.print("Enter your email address: ");
			String email = sc.next();
			psmt.setString(5, email);
			
			System.out.print("Enter your phone number: ");
			long phone = sc.nextLong();
			psmt.setLong(6, phone);
			
			psmt.execute();
			
			String sql1 = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1;";
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql1);
			
			if(rs.next()) {
				c.setUserID(rs.getInt("customer_id"));
				int userID = c.getUserID();
				
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				String name = c.getFirstName() + " " + c.getLastName();

				
				createAccountInfo(userID, name);
			}
			return true;
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createAccountInfo(int userID, String name) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "INSERT INTO account(account_name, balance, account_type, approved, customer_fk) VALUES(?,?,?,?,?);";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, "default");
			psmt.setDouble(2, 0.00);
			psmt.setString(3, "Checkings");
			psmt.setBoolean(4, false);
			psmt.setInt(5, userID);
			
			psmt.execute();
			
			System.out.println();
			log.info("New account created");
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" Welcome to your new account " + name + "!");
			as.getAccountByID(userID);
			homePage();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
/*---------------------------------------------------------------------------------------------------------*/	
/*BASICS															*/
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void retry() {
		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void signin() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Log in \n 2. Create Account \n 3. Register Login \n 4. Exit");
		System.out.println("\n ----------------------------------- \n");
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					login(); break;
				case 2: 
					createAccount(); break;
				case 3: 
					registerLogin(); break;
				case 4:
					logout(); break;
				default: 
					System.out.println("Wrong input. Try again"); 
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login() {
		System.out.println("\n ----------------------------------- \n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println(" CUSTOMER LOGIN \n");
			System.out.print(" Username: ");
			String username = sc.next();
			
			System.out.print(" Password: ");
			String password = sc.next();
			
			System.out.println();
			userLogin(username,password);
	
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public Customer userLogin(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM customer WHERE user_name=? AND pass_word=?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
	
			if(rs.next()) {
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				c.setUserID(rs.getInt("customer_id"));
				System.out.println(" Welcome " + c.getFirstName() + " " + c.getLastName()+ "!");
				
				int userID = c.getUserID();
				as.getAccountByID(userID);
				homePage();
				return c;
			}else {
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void homePage() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Personal Information \n 2. Transaction \n 3. Exit");
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			homeOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void homeOption(int choice) {
		switch(choice){
			case 1: 
				as.getCustomerByID(c.getUserID());
				infoOption();
				break;
			case 2: 
				//getRecentTrans(); //TOP 5
				transOption();
				break;
			case 3: 
				logout(); 
				break;
			default: 
				System.out.println("Invalid Input"); retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void infoOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Update Information \t 2.Exit ");
		System.out.println("\n ----------------------------------- \n");
		int choice = sc.nextInt();
		switch(choice) {
			case 1: 
				updateCustomer(c.getUserID()); 
				homePage();
				break;
			case 2:  
				homePage();
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
		}
		
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public void transOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" 1. Transaction History \n 2. Deposit \n 3. Withdraw \n 4. Transfer \n 5. Exit");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				//getTransHistory();
				homePage();
				break;
			case 2:
				//as.deposit();
				homePage();
				break;
			case 3:
				//as.withdraw();
				homePage();
				break;
			case 4:
				//as.transfer();
				homePage();
				break;
			case 5:
				homePage();
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

}
