package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.*;
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class CustomerService extends UserService{
	Account a = new Account();
	Customer c = new Customer();
	Transaction t = new Transaction();
	Scanner sc = new Scanner(System.in);
	
	private static final Logger log = LogManager.getLogger(CustomerService.class);

	Statement stmt;
	PreparedStatement psmt;
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public boolean getTotal(int userID,String accType, int money) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql="CREATE OR REPLACE FUNCTION get_total() RETURNS NUMERIC\r\n" + 
					"AS $$ \r\n" + 
					"SELECT SUM(balance) FROM account WHERE customer_fk=" + userID + ";\r\n" + 
					"$$ LANGUAGE SQL; "+
					"INSERT INTO transactions(transaction_type, transaction_amount,total_balance,customer_fk)VALUES" + 
					"( '" + accType + "'," + money + ", get_total(), " + userID + ");";
			stmt = conn.createStatement();
			stmt.execute(sql);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

/*---------------------------------------------------------------------------------------------------------*/	
	
	public boolean withdraw(int userID) {
		getOpenAccounts(userID);
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" Which Account Number to withdraw from? ");
		int accID = sc.nextInt();

		getAccount(userID, accID); double balance = a.getBalance();
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" Enter the amount to withdraw: ");
		int money = sc.nextInt();
		
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance-? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accID);
			
			psmt.execute();
			getTotal(userID ,"Withdraw", money);
			System.out.println();
			log.info("Withdrawn complete");
			
			getAccounts(userID);
			homePage();
//			if(money > balance) {
//				psmt.setInt(1, money);
//				psmt.setInt(2, userID);
//				psmt.setInt(3, accID);
//				System.out.println("Cannot withdraw more than within the balance");
//			}else if(money <=0) {
//				psmt.setInt(1, money);
//				psmt.setInt(2, userID);
//				psmt.setInt(3, accID);
//				System.out.println("Cannot withdraw 0 or negative value");
//			}else {
//				psmt.setInt(1, money);
//				psmt.setInt(2, userID);
//				psmt.setInt(3, accID);
//				
//				getTotal(userID,"Withdraw", money);
//				getAccount(userID, accID);
//			}
//			psmt.execute();
//			System.out.println("Amount Withdrawn: "+ money + "Current Balance: " + balance);
//			homePage();
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

/*---------------------------------------------------------------------------------------------------------*/
//	public boolean depositMoney(int userID, int accID, int money) {
//		
//	}
	
	public boolean deposit(int userID) {
		getOpenAccounts(userID);
				
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" Which Account Number to deposit to? ");
		int accID = sc.nextInt();
		
		getAccount(userID, accID); double balance = a.getBalance();
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" Enter the amount to deposit: ");
		int money = sc.nextInt();
		
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance+? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accID);
			
			psmt.execute();
			getTotal(userID ,"Deposit", money);
			System.out.println();
			log.info("Deposit complete");
			getAccounts(userID);
			homePage();

//			if(money <= 0) {
//			System.out.println("Cannot withdraw 0 or negative value");
//			homePage();
//			
//			}else {
//			psmt.setInt(1, money);
//			psmt.setInt(2, userID);
//			psmt.setInt(3, accID);	
//			
//			getTotal(userID ,"Deposit", money);
//			getAccount(userID,accID);
//			}
			
//			psmt.execute();
//			System.out.println("Amount Deposit: "+ money + "Current Balance: " + balance);
//			homePage();
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
/*---------------------------------------------------------------------------------------------------------*/
		

	public boolean transfer(int userID) {
		getOpenAccounts(userID);
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" Which Account Number to transfer from? ");
		int accNum = sc.nextInt();
		System.out.print(" Which Account Number to transfer to? ");
		int accNum2 = sc.nextInt();
		System.out.print(" What is the amount to transfer? ");
		int money = sc.nextInt();
		
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance-? WHERE customer_fk=? AND account_id=?;" + 
					"UPDATE account SET balance=balance+? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accNum);
			psmt.setInt(4, money);
			psmt.setInt(5, userID);
			psmt.setInt(6, accNum2);
			
			psmt.execute();
			getTotal(userID ,"Transfer", money);
			System.out.println();
			log.info("Transfer complete");
			getAccounts(userID);
			homePage();
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
/*---------------------------------------------------------------------------------------------------------*/	
/*SELECT															*/
/*---------------------------------------------------------------------------------------------------------*/			

	public List <Transaction> getTransaction(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql="SELECT * FROM transactions WHERE customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			psmt.setInt(1, userID);
			List<Transaction> transactions = new ArrayList<Transaction>();
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				t.setAccID(userID);
				t.setAccType(rs.getString("transaction_type"));
				t.setAmount(rs.getInt("transaction_amount"));
				t.setTotalBalance(rs.getLong("total_balance"));
				t.setTransID(rs.getInt("transaction_id"));
				
				transactions.add(t);
				System.out.println(t);
			}
			return transactions;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------*/
	
	public Customer getCustomer(int userID) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM customer WHERE customer_id=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			psmt.setInt(1, userID);

			ResultSet rs = psmt.executeQuery();
	
			if(rs.next()) {
				c.setUserID(rs.getInt("customer_id"));
				c.setUserName(rs.getString("user_name"));
				c.setPassWord(rs.getString("pass_word"));
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				c.setEmail(rs.getString("email"));
				c.setNumber(rs.getLong("phone_number"));
	
				System.out.println(c);
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
	public Account getAccount(int userID, int accID) {	
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE customer_fk=? AND account_id=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			psmt.setInt(1, userID);
			psmt.setInt(2, accID);
			
			ResultSet rs = psmt.executeQuery();
	
			while(rs.next()) {
				a.setAccNumber(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setAccID(rs.getInt("customer_fk"));

				System.out.println(a);
			}
		return a;
		
		}catch(SQLException e){
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public List<Account> getAccounts(int userID) {	
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			psmt.setInt(1, userID);
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = psmt.executeQuery();
	
			while(rs.next()) {
				a.setAccNumber(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setAccID(rs.getInt("customer_fk"));
				
				accounts.add(a);
				System.out.println(a);
			}
		return accounts;
		
		}catch(SQLException e){
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public List<Account> getPendingAccounts(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=false AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			psmt.setInt(1, userID);
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				a.setAccNumber(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setAccID(rs.getInt("customer_fk"));
				
				accounts.add(a);
				System.out.println(a);
			}else {
			System.out.println("No pending account...");
			}
			
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getOpenAccounts(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=true AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			psmt.setInt(1, userID);

			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				a.setAccNumber(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setAccID(rs.getInt("customer_fk"));
				
				accounts.add(a);
				System.out.println(a);
			}
			
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

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
				System.out.println();
				log.info("Email Updated");
				break;
			case 2: 
				String sql2 ="UPDATE customer SET phone_number=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql2);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Phone Number: ");
				
				long phone = sc.nextLong();
				psmt.setLong(1, phone);
				System.out.println();
				log.info("Phone Number Updated");
				break;
			case 3: 
				String sql3 ="UPDATE customer SET pass_word=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql3);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update password: ");
				
				String password = sc.next();
				psmt.setString(1, password);
				System.out.println();
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
			String sql ="INSERT INTO customer (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?);";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print("Create your new username: ");
			String username = sc.next();
			
			System.out.print("Create your new password: ");
			String password = sc.next();
			
			System.out.print("Enter your first name: ");
			String firstName = sc.next();
			psmt.setString(1, firstName);
			
			System.out.print("Enter your last name: ");
			String lastName = sc.next();
			psmt.setString(2, lastName);
			
			System.out.print("Enter your email address: ");
			String email = sc.next();
			psmt.setString(3, email);
			
			System.out.print("Enter your phone number: ");
			long phone = sc.nextLong();
			psmt.setLong(4, phone);
			
			psmt.execute();
			
			String sql1 = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1;";
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql1);
			
			if(rs.next()) {
				c.setUserID(rs.getInt("customer_id"));
				int userID = c.getUserID();
				
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				String fullName = c.getFirstName() + " " + c.getLastName();
				
				createAccountInfo(userID, fullName, username, password);
			}
			return true;
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public boolean createAccountInfo(int userID, String fullName, String username, String password) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "INSERT INTO account(account_name, balance, account_type, approved, customer_fk) VALUES(?,?,?,?,?);"+
					"INSERT INTO users(user_name, pass_word, customer_fk) VALUES (?,?,?);";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, "default");
			psmt.setDouble(2, 0.00);
			psmt.setString(3, "Checkings");
			psmt.setBoolean(4, false);
			psmt.setInt(5, userID);
			
			psmt.setString(6, username);
			psmt.setString(7, password);
			psmt.setInt(8, userID);
			
			psmt.execute();
			
			System.out.println();
			log.info("New account created");
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" Welcome to your new account " + fullName + "!");
			getAccounts(userID);
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
				getAccounts(userID);
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
		System.out.println(" 1. Personal Information \n 2. Transaction \n 3. Log Out");
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			homeOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			logout();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void homeOption(int choice) {
		switch(choice){
			case 1: 
				getCustomer(c.getUserID());
				infoOption();
				break;
			case 2: 
				getTransaction(c.getUserID());
				transOption();
				break;
			case 3: 
				logout(); 
				break;
			default: 
				System.out.println("Invalid Input"); homePage();
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
				homePage();
		}
		
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public void transOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Transaction History \n 2. Deposit \n 3. Withdraw \n 4. Transfer \n 5. Exit");
		System.out.println("\n ----------------------------------- \n");
		
		int userID = c.getUserID();
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				getTransaction(userID);
				homePage();
				break;
			case 2:
				deposit(userID);
				homePage();
				break;
			case 3:
				withdraw(userID);
				homePage();
				break;
			case 4:
				transfer(userID);
				homePage();
				break;
			case 5:
				homePage();
				break;
			default: 
				System.out.println("Invalid input"); 
				homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

}
