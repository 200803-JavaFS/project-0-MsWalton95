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
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class EmployeeService extends UserService implements EmployeeDAO {
	Customer c = new Customer();
	Account a = new Account();
	Employee e = new Employee();
	AccountService  as = new AccountService(); 
	
	Scanner sc = new Scanner(System.in);
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	Statement stmt;
	PreparedStatement psmt;
	
	
/*---------------------------------------------------------------------------------------------------------*/	
	/*DELETE, INSERT, UPDATE	
/*---------------------------------------------------------------------------------------------------------*/	
		
	public boolean denyAccountByID(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql="DELETE FROM account WHERE account_id=? AND approved=false AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.print("Please enter account number: ");
			int accID = sc.nextInt();
			psmt.setInt(1, accID);
			
			psmt.setInt(2, userID);
			psmt.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
/*---------------------------------------------------------------------------------------------------------*/	

	public boolean approveAccountByID(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql="UPDATE account SET approved=true WHERE account_id=? AND approved=false AND customer_fk=?;";
			psmt = conn.prepareStatement(sql);
			
			System.out.print("Please enter account number: ");
			int accID = sc.nextInt();
			psmt.setInt(1, accID);
			
			psmt.setInt(2, userID);
			psmt.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		
/*---------------------------------------------------------------------------------------------------------*/	
/*SELECT
 /*---------------------------------------------------------------------------------------------------------*/	
	
	//public List<> getAllTransactions(){}	
 
/*---------------------------------------------------------------------------------------------------------*/	
		
	public List<Customer> getAllCustomers(){
		try(Connection conn = ConnectionDAO.connect()) {
			String sql = "SELECT * FROM customer";
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
		
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getAllAccounts(){
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account";
			stmt = conn.createStatement();
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("\n ----------------------------------- \n");
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
			
			log.info("\n"+"Retrieve All Account information");
			return accounts;
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getAllPendingAccounts() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=false";
			stmt = conn.createStatement();
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("\n ----------------------------------- \n");

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

			log.info("\n"+"Retrieve All Pending Accounts");
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public List<Account> getAllOpenAccounts() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=true";
			stmt = conn.createStatement();
			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("\n ----------------------------------- \n");
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
			
			log.info("Retrieve All Open Accounts");
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

/*---------------------------------------------------------------------------------------------------------*/	
/*BASIC															*/
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void retry() {
		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void login() {
		System.out.println("\n ----------------------------------- \n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println("EMPLOYEE LOGIN \n");
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			String password = sc.next();
			
			System.out.println();
			userLogin(username,password);
			
	
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			super.retry();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public Employee userLogin(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM employee WHERE user_name=? AND pass_word=? AND administator=false;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
	
			if(rs.next()) {
				e.setEmployeeID(rs.getInt("employee_id"));
	
				System.out.println("Welcome Employee #" + e.getEmployeeID()+ "!");
				homePage();
				return e;
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
		super.homePage();
	
		try {
			int choice = sc.nextInt();
			homeOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void homeOption(int choice){
		switch(choice) {
			case 1: customerInfo(); break;
			case 2: customerAccount();break;
			case 3: customerTrans();break;
			case 4: logout(); break;
			default: System.out.println("Invalid Input"); retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void customerInfo() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println("1. View All Customers \n 2. View Customer \n 3. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			infoOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void infoOption(int choice) {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Please enter customer ID: ");
		int userID = sc.nextInt();
		
		switch(choice) {
			case 1: 
				getAllCustomers(); 
				homePage();
				break;
			case 2: 
				as.getCustomerByID(userID); 
				homePage();
				break;
			case 3: 
				homePage(); 
				break;
			default: System.out.println("Invalid input"); retry();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void customerAccount() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println("1. View All Accounts \n 2. View Account \n 3. Update Account \n 4. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			accountOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			retry();
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
				retry();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void getAllAccountOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Accounts \n 2. View All Pending Accounts \n 3. View All Open Accounts \n 4. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				getAllAccounts(); 
				homePage();
				break;
			case 2: 
				getAllPendingAccounts();
				homePage();
				break;
			case 3:
				getAllOpenAccounts();
				homePage();
				break;
			case 4: 
				homePage(); 
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
		}
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public void getAccountOption() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Please enter customer ID: ");
		int userID = sc.nextInt();
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println(" 1. View Account \n 2. View Pending Accounts \n 3. View Open Accounts \n 4. Exit" );
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				
				as.getAccountByID(userID); 
				homePage();
				break;
			case 2: 
				as.getPendingAccountsByID(userID);
				homePage();
				break;
			case 3:
				as.getOpenAccountsByID(userID);
				homePage();
				break;
			case 4: 
				homePage(); 
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	public void updateAccountOption() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Please enter customer ID: ");
		int userID = sc.nextInt();
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println(" 1. Approve Account \n 2. Deny Accounts \n 3. Exit" );
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				approveAccountByID(userID);
				homePage();
				break;
			case 2: 
				denyAccountByID(userID);
				homePage();
				break;
			case 3:
				homePage(); 
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
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
			System.out.println("Invalid Input");
			retry();
		}
	}
	
	
	/*---------------------------------------------------------------------------------------------------------*/	

	public void editOption(int choice) {
		
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Please enter customer ID: ");
		int userID = sc.nextInt();
				
		switch(choice) {
			case 1: 
				approveAccountByID(userID);
				homePage();
				break;
			case 2: 
				denyAccountByID(userID);
				homePage();
				break;
			case 3: 
				homePage(); 
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void customerTrans() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println("  1. View All Customer Transactions \n 2. View Customer Transaction \n 3. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			transOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	///NEED TO WORK ON TRANSACTION HISTORY
	public void transOption(int choice) {
		switch(choice) {
			case 1: 
				//getAllTransaction();
				break;
			case 2:
				///getTransaction();
				break;
			case 3: 
				homePage(); 
				break;
			default: 
				System.out.println("Invalid input"); 
				retry();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	


	
/*---------------------------------------------------------------------------------------------------------*/	

	
	
/*---------------------------------------------------------------------------------------------------------*/	

}
