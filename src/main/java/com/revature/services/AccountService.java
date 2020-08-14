package com.revature.services;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class AccountService extends UserService {
	Account a = new Account();
	Customer c = new Customer();

	Scanner sc = new Scanner(System.in);

	private static final Logger log = LogManager.getLogger(Driver.class);
	
	Statement stmt;
	PreparedStatement psmt;
/*---------------------------------------------------------------------------------------------------------*/	
/*SELECT															*/

	/*---------------------------------------------------------------------------------------------------------*/	
	//Complete
	public Customer getCustomerByID() {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM customer WHERE customer_id=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print("Enter CustomerID: ");
			int userID = sc.nextInt();
			psmt.setInt(1, userID);
			System.out.println("\n" + "-----------------------------------" + "\n");

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
	//Complete
	public Customer getCustomerByID(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM customer WHERE user_name=? AND pass_word=?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
			System.out.println("\n" + "-----------------------------------" + "\n");
	
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
	//Completed
	public List<Account> getAccountByID() {	
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print("Enter CustomerID: ");
			int userID = sc.nextInt();
			psmt.setInt(1, userID);
			System.out.println("\n" + "-----------------------------------" + "\n");

			
			List<Account> accounts = new ArrayList<Account>();
			
			ResultSet rs = psmt.executeQuery();
	
			while(rs.next()) {
				a.setAccNumber(rs.getInt("account_id"));
				a.setAccName(rs.getString("account_name"));
				a.setBalance(rs.getDouble("balance"));
				a.setAccType(rs.getString("account_type"));
				a.setApproved(rs.getBoolean("approved"));
//				a.setAccID(rs.getInt("customer_fk"));
				
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
	//Complete
	public List<Account> getPendingAccountsByID() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=false AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print("Enter CustomerID: ");
			int userID = sc.nextInt();
			psmt.setInt(1, userID);
			System.out.println("\n" + "-----------------------------------" + "\n");
			
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
	
	//Complete
	public List<Account> getOpenAccountsByID() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account WHERE approved=true AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print("Enter CustomerID: ");
			int userID = sc.nextInt();
			psmt.setInt(1, userID);
			System.out.println("\n" + "-----------------------------------" + "\n");

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
/*DELETE, INSERT, UPDATE															*/
/*---------------------------------------------------------------------------------------------------------*/	

	
	public boolean deleteAccountByID() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "DELETE FROM account WHERE account_name=? AND customer_fk=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.println("Account Name: ");
			String accName = sc.next();	
			String def = "default";
			accName = accName.toLowerCase();
			
			if(accName.equals(def)) {
				psmt.setString(1, null);
				System.out.println("Cannot delete default account");
			}else {
			psmt.setString(1, accName);
			}
			
			System.out.println("Customer ID: ");
			int userID = sc.nextInt();
			psmt.setInt(2, userID);
			
			psmt.execute();
			System.out.println("Account '" + accName + "' is deleted");
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	//Complete
	public boolean createAccountByID() {
		try(Connection conn = ConnectionDAO.connect()){
		
			String sql = "INSERT INTO account(account_name, balance, account_type, approved, customer_fk)"
					+ " VALUES(?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print("Account Name: ");
			String accName = sc.nextLine();
			accName = accName.toLowerCase();
			
			psmt.setString(1, accName);
			psmt.setDouble(2, 0.00);
			
			System.out.print("1. Checking \t 2.Saving: ");
			int accType = sc.nextInt();
			
			switch(accType) {
				case 1: 
					psmt.setString(3,"Checking");break;
				case 2: 
					psmt.setString(3,"Saving");break;
				default: 
					System.out.println("Invalid Input");
			}
			psmt.setBoolean(4, false);
			
			System.out.println("CustomerID: ");
			int userID = sc.nextInt();
			psmt.setInt(5, userID);
		    
			psmt.execute();
			System.out.println("New account created");//Replace with Log
			System.out.println("\n" + "-----------------------------------" + "\n");

			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
/*---------------------------------------------------------------------------------------------------------*/
	
	public void withdraw() throws PSQLException {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM account";
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				int balance = rs.getInt("balance");	
				System.out.println("Current balance: " + balance + rs.getInt("account_id"));
			}
			
			String sql2 = "BEGIN; UPDATE account SET balance=balance-? "
					+ "WHERE customer_fk=? AND account_name=?;";
			psmt = conn.prepareStatement(sql2);

			System.out.print("Withdraw Amount: ");
			int money = sc.nextInt();
			
			if(money > rs.getInt("balance")) {
				
			}else if(money <= 0) {
				System.out.println("Cannot deposit negative value");
			}else {
				psmt.setInt(1, money);
			}
			
			System.out.print("User ID: ");
			int userID = sc.nextInt();
			psmt.setInt(2, userID);
			
			System.out.print("Account Name: ");
			String accName = sc.next();
			psmt.setString(3, accName);
			
			psmt.execute();
			
			System.out.println(rs.getInt("balance"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			ex.printStackTrace();
		}
		
	}

/*---------------------------------------------------------------------------------------------------------*/
	
	public void deposit() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "BEGIN; UPDATE account SET balance=balance+? "
					+ "WHERE customer_fk=? AND account_name=?;";
			psmt = conn.prepareStatement(sql);
			
			System.out.print("Withdraw Amount: ");
			int money = sc.nextInt();
			
			if(money <= 0) {
				System.out.println("Cannot deposit negative value");
				sql+="ROLLBACK;";
			}else {
				psmt.setInt(1, money);
				sql+="COMMIT;";
			}
			
			System.out.print("User ID: ");
			int userID = sc.nextInt();
			psmt.setInt(2, userID);
			
			System.out.print("Account Name: ");
			String accName = sc.next();
			psmt.setString(3, accName);
			
			ResultSet rs = psmt.executeQuery();
			
			System.out.println("Deposit: " + money + "\n Current Balance: " + rs.getInt("balance"));
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	
/*---------------------------------------------------------------------------------------------------------*/
	
	public void transfer() {}
	
/*---------------------------------------------------------------------------------------------------------*/
	
	public void retry() {
		super.retry();
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1: 
			//withdraw();break;
		case 2: 
			option2(); break;
		default: 
			System.out.println("Wrong input. Try again"); 
			retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/
	


/*---------------------------------------------------------------------------------------------------------*/
	
	public void option2() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Withdraw \n" + "2. Deposit \n" + "3. Transfer \n" + "4.Add Account" + "5. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1: 
			//withdraw();break;
		case 2: 
			deposit(); break;
		case 3: 
			transfer(); break;
		case 4:
			//createAccount();break;
		case 5:
			//option();break;
		default: 
			System.out.println("Wrong input. Try again"); 
			option2();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/
	
//	public void option3() {
//		System.out.println("\n" + "-----------------------------------" + "\n");
//		System.out.println("1. Checkings \n" + "2. Savings \n" + "3. Growth \n" + "4. Exit");
//		System.out.println("\n" + "-----------------------------------" + "\n");
//		
//		int choice = sc.nextInt();
//		
//		switch(choice) {
//			case 1: 
//				withdraw();break;
//			case 2: 
//				deposit(); break;
//			case 3: 
//				transfer(); break;
//			default: 
//				System.out.println("Wrong input. Try again"); 
//				option2();
//		}
//	}
	
/*---------------------------------------------------------------------------------------------------------*/

	
	
	
}
