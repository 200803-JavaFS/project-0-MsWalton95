package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.Customer;
import com.revature.utility.ConnectionDAO;
import com.revature.utility.Driver;

public class CustomerService extends UserService{
	AccountService acc = new AccountService();
	Customer c = new Customer();
	
	Scanner sc = new Scanner(System.in);

	Statement stmt;
	PreparedStatement psmt;
	
	
/*---------------------------------------------------------------------------------------------------------*/	
/*UPDATE, INSERT															*/
/*---------------------------------------------------------------------------------------------------------*/	
	//Error
	public boolean update(int userID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "UPDATE customer SET ?=? WHERE customer_id=?";
			psmt = conn.prepareStatement(sql);
	
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.println("1. Email \n" + "2. Phone Number \n" + "3. Reset Password");
			System.out.println("\n" + "-----------------------------------" + "\n");
			
			int edit = sc.nextInt();
			
			switch(edit) {
				case 1: 
					psmt.setString(1,"email"); 
					String email = sc.next();
					psmt.setString(2, email);
					break;
				case 2: 
					psmt.setString(1,"phone_number"); 
					Long phoneNumber = sc.nextLong();
					psmt.setLong(2, phoneNumber);
					break;
				case 3:
					psmt.setString(1, "pass_word");
					String password = sc.next();
					psmt.setString(2, password);
					break;
				default: 
					System.out.println("Invalid Input");
			}
			psmt.setInt(3, userID);
			psmt.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	public boolean register() {
		try(Connection conn = ConnectionDAO.connect()){
			String sql = "INSERT INTO customer (user_name, pass_word, first_name, last_name, email, phone_number) "
					+ "VALUES(?, ?, ?, ?, ?)";
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*---------------------------------------------------------------------------------------------------------*/	
	
	public void create() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		
		try {
			System.out.print("First Name: ");
			String firstname = sc.next();
			
			System.out.print("Last Name: ");
			String lastname = sc.next();
			
			System.out.print("Email: ");
			String email = sc.next();
			
			System.out.print("Phone Number: ");
			int number = sc.nextInt();
			
			System.out.print("Create Username: ");
			String username = sc.next();
			
			System.out.print("Create Password: ");
			String password = sc.next();
			
			
			
			System.out.print("Welcome " + firstname + " !");
		
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
/*---------------------------------------------------------------------------------------------------------*/	
/*BASICS															*/
/*---------------------------------------------------------------------------------------------------------*/	

	public void signin() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Log in \n" + "2. Create Account \n" + "3. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");
		try {
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				login(); break;
			case 2: 
				register(); break;
			case 3:
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
		System.out.println("\n" + "-----------------------------------" + "\n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println("CUSTOMER LOGIN \n");
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
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
	
				System.out.println("Welcome " + c.getFirstName() + " " + c.getLastName()+ "!");
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
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.println("1. Manage Accounts \n" + "2. Update Personal Information \n" + "3. Exit");
		System.out.println("\n" + "-----------------------------------" + "\n");	
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void retry() {
		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void accountOption() {}
	
/*---------------------------------------------------------------------------------------------------------*/	
	


}
