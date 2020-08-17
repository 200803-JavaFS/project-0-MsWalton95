package com.revature.services;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

import com.revature.dao.AccountDAO;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class AccountService extends UserService implements AccountDAO {
	Account a = new Account();
	Customer c = new Customer();
	Scanner sc = new Scanner(System.in);

	private static final Logger log = LogManager.getLogger(Driver.class);
	
	Statement stmt;
	PreparedStatement psmt;
	
	
/*---------------------------------------------------------------------------------------------------------*/	
/*DELETE, INSERT, UPDATE															*/
/*---------------------------------------------------------------------------------------------------------*/	
		
	public boolean withdraw(int userID) {
		getAccountByID(userID);
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance-? WHERE customer_fk=? AND account_id=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print("Which Account Number to withdraw from? ");
			int accNumber = sc.nextInt();

			System.out.print("Enter the amount to withdraw: ");
			int money = sc.nextInt();
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accNumber);
			
			psmt.execute();
			
			System.out.println("\n ----------------------------------- \n");
			//System.out.println("Amount Deposit: " + money + "\t Current Balance: " + currentBalance + "\t Total Balance: " + totalBalance);
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
		
	public boolean deposit(int userID) {
		getAccountByID(userID);
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance+? WHERE customer_fk=? AND account_id=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print("Which Account Number to deposit to? ");
			int accNumber = sc.nextInt();

			System.out.print("Enter the amount to deposit: ");
			int money = sc.nextInt();
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accNumber);
			
			psmt.execute();
			
			System.out.println("\n ----------------------------------- \n");
			//System.out.println("Amount Deposit: " + money + "\t Current Balance: " + currentBalance + "\t Total Balance: " + totalBalance);
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
		

	public boolean transferFunds(int userID) {
		getAccountByID(userID);
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance-? WHERE customer_fk=? AND account_id=?;" + 
					"UPDATE account SET balance=balance+? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print("Which Account Number to transfer from? ");
			int accNum = sc.nextInt();
			System.out.print("Which Account Number to transfer to? ");
			int accNum2 = sc.nextInt();
			System.out.print("What is the amount to transfer? ");
			int money = sc.nextInt();
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accNum);
			psmt.setInt(4, money);
			psmt.setInt(5, userID);
			psmt.setInt(6, accNum2);
			
			psmt.execute();
			
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
/*	Optional Transfer to Others
 * public void transfer(int userID) {
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" 1. Transfer funds between accounts \n 2. Send funds to others \n 3. Request funds from others \n 4. Exit");
		System.out.println("\n ----------------------------------- \n");
		int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				transferFunds(userID);
				break;
			case 2: 
				sendFundsToOthers(userID);
				break;
			case 3: 
				requestFundsFromOthers(userID);
				break;
			case 4: 
				homePage(); 
				break;
			default:
				System.out.println("Invalid Input");
			//retry();
			}
	}
	
	public boolean sendFundsToOthers(int userID) {
		getAccountByID(userID);
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE account SET balance=balance? WHERE customer_fk=?, account_id=?"+
					"UPDATE account SET balance=balance? WHERE customer_fk=?, account_id=?";
			psmt = conn.prepareStatement(sql);
			
			System.out.print("Which Account Number to transfer from? ");
			int accNum = sc.nextInt();
			System.out.print("Enter the Email to transfer to: ");
			int accNum2 = sc.nextInt();
			System.out.print("What is the amount to transfer? ");
			int money = sc.nextInt();
			
			psmt.setInt(1, money);
			psmt.setInt(2, userID);
			psmt.setInt(3, accNum);
			psmt.setInt(4, money);
			psmt.setInt(5, userID);
			psmt.setInt(6, accNum2);
			
			psmt.execute();
		
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
*/			
/*---------------------------------------------------------------------------------------------------------*/	
/*SELECT															*/
/*---------------------------------------------------------------------------------------------------------*/	

	public void retry() {

	}		

/*---------------------------------------------------------------------------------------------------------*/	
	//Optional - Transacation History
	 //public List<> getTransByID(){}	
	 
/*---------------------------------------------------------------------------------------------------------*/
	
	public Customer getCustomerByID(int userID) {	
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
	
	public List<Account> getAccountByID(int userID) {	
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

	public List<Account> getPendingAccountsByID(int userID) {
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
	
	public List<Account> getOpenAccountsByID(int userID) {
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
	
	
}
