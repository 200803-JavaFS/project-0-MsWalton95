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
/*DELETE, INSERT, UPDATE															*/
/*---------------------------------------------------------------------------------------------------------*/	
		
	public boolean withdraw() throws PSQLException {
		try(Connection conn = ConnectionDAO.connect()){
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			ex.printStackTrace();
		}
		return false;
	}

/*---------------------------------------------------------------------------------------------------------*/
		
	public boolean deposit() {
		try(Connection conn = ConnectionDAO.connect()){
			return true;
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
/*---------------------------------------------------------------------------------------------------------*/
		
	public boolean transfer() {
		try(Connection conn = ConnectionDAO.connect()){
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

	public void retry() {

	}		

/*---------------------------------------------------------------------------------------------------------*/	
	
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
	
	public Customer getCustomerByID(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM customer WHERE user_name=? AND pass_word=?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
			System.out.println("\n ----------------------------------- \n");
	
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
