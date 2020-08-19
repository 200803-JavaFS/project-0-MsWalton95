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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.Transaction;
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class EmployeeService extends CustomerService implements EmployeeDAO {
	Customer c = new Customer();
	Account a = new Account();
	Employee e = new Employee();
	Transaction t = new Transaction();
	
	CustomerService cs = new CustomerService();
	
	Scanner sc = new Scanner(System.in);
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	Statement stmt;
	PreparedStatement psmt;
	
	
/*---------------------------------------------------------------------------------------------------------*/	
/*	Update Pending Accounts	
/*---------------------------------------------------------------------------------------------------------*/	
		
	public boolean denyAccount() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print(" Please enter customer ID: ");
		int userID = sc.nextInt();
		cs.getPendingAccounts(userID);
		try(Connection conn = ConnectionDAO.connect()){
			String sql="DELETE FROM account WHERE account_id=? AND approved=false AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print(" Please enter account ID: ");
			int accID = sc.nextInt();
			psmt.setInt(1, accID);
			
			psmt.setInt(2, userID);
			psmt.execute();
			System.out.println();
			log.info("Account '"+accID+"' has been denied");
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			sc.next();
			t1.run();
		}catch(SQLException e) {
			System.out.println("Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
		return false;
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public boolean approveAccount() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print(" Please enter customer ID: ");
		int userID = sc.nextInt();
		cs.getPendingAccounts(userID);
		try(Connection conn = ConnectionDAO.connect()){
			String sql="UPDATE account SET approved=true WHERE account_id=? AND approved=false AND customer_fk=?;";
			psmt = conn.prepareStatement(sql);
			
			System.out.print(" Please enter account ID: ");
			int accID = sc.nextInt();
			psmt.setInt(1, accID);
			
			psmt.setInt(2, userID);
			psmt.execute();
			System.out.println();
			log.info("Account '"+accID+"' has been approved");
			return true;
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
		return false;
	}
		
/*---------------------------------------------------------------------------------------------------------*/	
/*	Retrieve Information
/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Transaction> getAllTransactions(){
		try(Connection conn = ConnectionDAO.connect()){
			String sql="SELECT * FROM transactions ORDER BY transaction_id DESC";
			stmt = conn.createStatement();
			
			List<Transaction> transactions = new ArrayList<Transaction>();
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("\n ----------------------------------- \n");
			
			while(rs.next()) {
				t.setUserID(rs.getInt("customer_fk"));
				t.setAccType(rs.getString("transaction_type"));
				t.setAmount(rs.getInt("transaction_amount"));
				t.setTotalBalance(rs.getLong("total_balance"));
				t.setTransID(rs.getInt("transaction_id"));
				t.setTimestamp(rs.getTimestamp("updated_time"));
				
				transactions.add(t);
				System.out.println(t);
			}
			System.out.println();
			log.info("Retrieve All Transaction History");
			return transactions;

		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
		System.out.println("No transactions have been made...");
		return null;
	}
 
/*---------------------------------------------------------------------------------------------------------*/	
		
	public List<Customer> getAllCustomers(){
		try(Connection conn = ConnectionDAO.connect()) {
			String sql = "SELECT * FROM customer ORDER BY customer_id";
			stmt = conn.createStatement();
			
			List<Customer> customers = new ArrayList<Customer>();
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("\n ----------------------------------- \n");

			while(rs.next()) {
				c.setUserID(rs.getInt("customer_id"));
				c.setUserName(rs.getString("user_name"));
				c.setPassWord(rs.getString("pass_word"));
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				c.setEmail(rs.getString("email"));
				c.setNumber(rs.getLong("phone_number"));
				
				customers.add(c);
				System.out.println(c);
			}
			System.out.println();
			log.info("Retrieve All Customer Information");
			return customers;
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
			
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getAllAccounts(){
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account ORDER BY customer_fk, account_id";
			stmt = conn.createStatement();
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("\n ----------------------------------- \n");
			while(rs.next()) {
				a.setAccID(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setUserID(rs.getInt("customer_fk"));
				
				accounts.add(a);
				System.out.println(a);
			}
			System.out.println();
			log.info("Retrieve All Account information");
			return accounts;
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
		return null;
	}

/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getAllPendingAccounts() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=false ORDER BY customer_fk, account_id";
			stmt = conn.createStatement();
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("\n ----------------------------------- \n");

			while(rs.next()) {
				a.setAccID(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setUserID(rs.getInt("customer_fk"));
				
				accounts.add(a);
				System.out.println(a);
			}
			System.out.println();
			log.info("Retrieve All Pending Accounts Information");
			return accounts;
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
		System.out.println("No pending accounts...");
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getAllOpenAccounts() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=true ORDER BY account_id";
			stmt = conn.createStatement();
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("\n ----------------------------------- \n");
			while(rs.next()) {
				a.setAccID(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
				a.setUserID(rs.getInt("customer_fk"));
				
				accounts.add(a);
				System.out.println(a);
			}
			System.out.println();
			log.info("Retrieve All Open Accounts Information");
			return accounts;
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			homePage();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
		return null;
	}

/*---------------------------------------------------------------------------------------------------------*/	
//	Login and Options
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void signin() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Log in \t 2. Exit");
		System.out.println("\n ----------------------------------- \n");
		try {
		int choice = sc.nextInt();
		option(choice);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t2.run();
		}catch(Exception e) {
			e.printStackTrace();
			signin();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void option(int choice) {
		switch(choice) {
			case 1: login();break;
			case 2: logout();break;
			default: System.out.println(" Invalid Input"); t2.run();
		}
	}	
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login() {
		System.out.println("\n ----------------------------------- \n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println(" EMPLOYEE LOGIN \n");
			System.out.print(" Username: ");
			String username = sc.next();
			
			System.out.print(" Password: ");
			String password = sc.next();
			
			System.out.println();
			empLogin(username,password);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t2.run();
		}catch(Exception e) {
			e.printStackTrace();
			signin();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public Employee empLogin(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM employee WHERE user_name=? AND pass_word=? AND administator=false;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
	
			if(rs.next()) {
				e.setEmployeeID(rs.getInt("employee_id"));
	
				System.out.println(" Welcome Employee #" + e.getEmployeeID()+ "!");
				homePage();
				return e;
			}else {
				System.out.println(" Username or password incorrect");
				t2.run();
				return null;
			}
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			signin();
		}catch(Exception e) {
			e.printStackTrace();
			signin();
		}
		return null;		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void homePage() {
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" 1. Customer Info \n 2. Customer Account \n 3. Customer Transactions \n 4. Log Out" );
			System.out.println("\n ----------------------------------- \n");
	
		try {
			int choice = sc.nextInt();
			homeOption(choice);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void homeOption(int choice){
		switch(choice) {
			case 1: customerInfo(); break;
			case 2: customerAccount();break;
			case 3: customerTrans();break;
			case 4: logout(); break;
			default: System.out.println(" Invalid Input"); t1.run();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void customerInfo() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Customers \n 2. View Customer \n 3. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			infoOption(choice);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void infoOption(int choice) {
		switch(choice) {
			case 1: 
				getAllCustomers(); 
				t1.run();
				break;
			case 2: 
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print(" Please enter customer ID: ");
				try {
					int userID = sc.nextInt();
					cs.getCustomer(userID); 
					t1.run();
				}catch(InputMismatchException e) {
					System.out.println(" Invalid Input");
					sc.next();
					t1.run();
				}catch(Exception e) {
					e.printStackTrace();
					homePage();
				}
				break;
			case 3: 
				t1.run(); 
				break;
			default: 
				System.out.println(" Invalid input"); 
				t1.run();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void customerAccount() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Accounts \n 2. View Account \n 3. Update Account \n 4. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			accountOption(choice);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void accountOption(int choice) {
		
		switch(choice) {
			case 1: 
				getAllAccountOption();
				break;
			case 2:
				getAccountOption();
				break;
			case 3: 
				updateAccountOption(); 
				break;
			case 4: 
				homePage();
				break;
			default: 
				System.out.println("Invalid input"); 
				t1.run();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void getAllAccountOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Accounts \n 2. View All Pending Accounts \n 3. View All Open Accounts \n 4. Exit" );
		System.out.println("\n ----------------------------------- \n");
		try{
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				getAllAccounts(); 
				t1.run();
				break;
			case 2: 
				getAllPendingAccounts();
				t1.run();
				break;
			case 3:
				getAllOpenAccounts();
				t1.run();
				break;
			case 4: 
				homePage();
				break;
			default: 
				System.out.println(" Invalid input"); 
				t1.run();
		}
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public void getAccountOption() {

		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println(" 1. View Account \n 2. View Pending Accounts \n 3. View Open Accounts \n 4. Exit" );
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 		
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print(" Please enter customer ID: ");
				try {
				int userID = sc.nextInt();
				cs.getAccounts(userID); 
				t1.run();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
				break;
			case 2: 
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print(" Please enter customer ID: ");
				try {
				int userID2 = sc.nextInt();
				cs.getPendingAccounts(userID2);
				t1.run();
				}catch(InputMismatchException e) {
					System.out.println("Invalid Input");
					sc.next();
					t1.run();
				}catch(Exception e) {
					e.printStackTrace();
					homePage();
				}
				break;
			case 3:
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print(" Please enter customer ID: ");
				try {
					int userID3 = sc.nextInt();
					cs.getOpenAccounts(userID3);
					t1.run();
				}catch(InputMismatchException e) {
					System.out.println(" Invalid Input");
					sc.next();
					t1.run();
				}catch(Exception e) {
					e.printStackTrace();
					homePage();
				}
				break;
			case 4: 
				homePage(); 
				break;
			default: 
				System.out.println(" Invalid input"); 
				sc.next();
				t1.run();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void updateAccountOption() {
		getAllPendingAccounts();
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println(" 1. Approve Account \n 2. Deny Accounts \n 3. Exit" );
		System.out.println("\n" + "-----------------------------------" + "\n");
		try {
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					approveAccount();
					t1.run();
					break;
				case 2: 
					denyAccount();
					t1.run();
					break;
				case 3:
					homePage(); 
					break;
				default: 
					System.out.println(" Invalid input"); 
					sc.next();
					t1.run();
			}
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void customerEdit() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println("  1. Approve Account \n 2. Deny Account \n 3. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			editOption(choice);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void editOption(int choice) {
		switch(choice) {
			case 1: 
				approveAccount();
				t1.run();
				break;
			case 2: 
				denyAccount();
				t1.run();
				break;
			case 3: 
				homePage();
				break;
			default: 
				System.out.println("Invalid input");
				t1.run();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void customerTrans() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Customer Transactions \n 2. View Customer Transaction \n 3. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			transOption(choice);
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e) {
			e.printStackTrace();
			homePage();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void transOption(int choice) {
		switch(choice) {
			case 1: 
				getAllTransactions();
				t1.run();
				break;
			case 2:
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print(" Please enter customer ID: ");
				try {
					int userID = sc.nextInt();
					cs.getTransaction(userID);
					t1.run();
				}catch(InputMismatchException e) {
					System.out.println(" Invalid Input");
					sc.next();
					homePage();
				}catch(Exception e) {
					e.printStackTrace();
					homePage();
				}
				break;
			case 3: 
				homePage();
				break;
			default: 
				System.out.println(" Invalid input"); 
				t1.run();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	


}
