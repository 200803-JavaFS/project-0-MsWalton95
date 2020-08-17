package com.revature.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Admin;
import com.revature.model.Employee;
import com.revature.utility.ConnectionDAO;

public class AdminService extends EmployeeService {
	Admin a = new Admin();
	
	private static final Logger log = LogManager.getLogger(Admin.class);
		
/*---------------------------------------------------------------------------------------------------------*/	
	public void updateAccountByID() {
		System.out.println("\n" + "-----------------------------------" + "\n");
		System.out.print("Please enter customer ID: ");
		int userID = sc.nextInt();
		try(Connection conn = ConnectionDAO.connect()){//Name/Balance/Type/Approved
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" 1. Please enter Account Number");
			System.out.println("\n ----------------------------------- \n");
			
			int accNumber = sc.nextInt();
			
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" 1. Account Name \n 2. Type \n 3. Approved \n 4. Exit");
			System.out.println("\n ----------------------------------- \n");
			
			int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				String sql1 ="UPDATE account SET account_name=? WHERE customer_fk=? AND account_id=?;";
				psmt = conn.prepareStatement(sql1);
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Account Name: ");
				String accName = sc.next();
				
				if(accName.equals("default")) {
					System.out.println("Cannot change to 'default' account");
					homePage();
				}else {
				psmt.setString(1, accName);
				}
				psmt.setInt(2, userID);
				psmt.setInt(3, accNumber);
				psmt.execute();
				
				log.info("Account Name Updated");
				homePage();
				break;
			case 2: 
				String sql2 ="UPDATE account SET account_type=? WHERE customer_fk=? AND account_id=?;";
				psmt = conn.prepareStatement(sql2);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Account Type \n");
				System.out.println();
				System.out.println(" 1. Checking \n 2. Savings \n 3. Exit");
				System.out.println("\n ----------------------------------- \n");
					int accType = sc.nextInt();
					switch(accType) {
					case 1:
						psmt.setString(1, "Checkings");
						break;
					case 2:
						psmt.setString(2, "Savings");
						break;
					default: 
						System.out.println("Invalid Input");
						//retry();
					}
				psmt.setInt(2, userID);
				psmt.setInt(3, accNumber);
				psmt.execute();
				log.info("Account Type Updated");
				homePage();
				break;
			case 3: 
				String sql3 ="UPDATE account SET approved=? WHERE customer_fk=? AND account_id=?;";
				psmt = conn.prepareStatement(sql3);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Account Status");
				System.out.println();
				System.out.println(" 1. Deny \n 2. Approve \n 3. Exit");
				System.out.println("\n ----------------------------------- \n");
					int accStatus = sc.nextInt();
					switch(accStatus) {
					case 1:
						psmt.setBoolean(1, false);
						break;
					case 2:
						psmt.setBoolean(2, true);
						break;
					default: System.out.println("Invalid Input");
					}
				psmt.setInt(2, userID);
				psmt.setInt(3, accNumber);
				psmt.execute();
				log.info("Account Status Updated");
				homePage();
				break;
			case 4: 
				homePage();
				break;
			default: 
				System.out.println("Invalid Input");
				//retry();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(InputMismatchException ex) {
			System.out.println("Invalid Input");
		}
	}
/*---------------------------------------------------------------------------------------------------------*/	
	public void updateCustomerByID() {
		try(Connection conn = ConnectionDAO.connect()){
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print("Please enter customer ID: ");
			int userID = sc.nextInt();
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" 1. First Name \n 2. Last Name \n 3. Email \n 4. Phone Number \n 5. Password \n 6. Exit");
			System.out.println("\n ----------------------------------- \n");
			
			int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				String sql1 ="UPDATE customer SET first_name=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql1);
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update First Name: ");
				String firstName = sc.next();
				psmt.setString(1, firstName);
				
				log.info("First Name Updated");
				break;
			case 2: 
				String sql2 ="UPDATE customer SET last_name=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql2);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Last Name: ");
				
				String lastName = sc.next();
				psmt.setString(1, lastName);
				
				log.info(" Last Name Updated");
				break;
			case 3: 
				String sql3 ="UPDATE customer SET email=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql3);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Email Address: ");
				
				String email = sc.next();
				psmt.setString(1, email);
				
				log.info("Email Updated");
				break;
			case 4: 
				String sql4 ="UPDATE customer SET phone_number=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql4);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Phone Number: ");
				
				long phone = sc.nextLong();
				psmt.setLong(1, phone);
				
				log.info("Phone Number Updated");
				break;
			case 5: 
				String sql5 ="UPDATE customer SET pass_word=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql5);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update password: ");
				
				String password = sc.next();
				psmt.setString(1, password);
				
				log.info("Password Updated");
				break;
			case 6: 
				homePage();
				break;
			default: 
				System.out.println("Invalid Input");
				//retry();
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
	//If customer changed banks or broke rules of using account then cancel account
	// Any money in bank can either be transfer to new bank or a mailed check
	public void cancelAccountByID() {
		System.out.println("\n ----------------------------------- \n");
		System.out.print("Please enter customer ID: ");
		int userID = sc.nextInt();
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" ARE YOU SURE YOU WANT TO CANCEL?");
		System.out.println();
		System.out.println(" 1. Yes \n 2. No" );
		System.out.println("\n ----------------------------------- \n");
		
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				try(Connection conn = ConnectionDAO.connect()){
				String sql ="DELETE FROM account WHERE customer_fk=?; DELETE FROM customer WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, userID);
				psmt.setInt(2, userID);
				
				psmt.execute();
				log.info("Account '" + userID + "' was cancelled");
				}catch(Exception e) {
					e.printStackTrace();
				}
				homePage();
				break;
			default:
				homePage();
				break;
		}
		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login() {
		System.out.println("\n ----------------------------------- \n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println(" ADMINISTRATOR LOGIN \n");
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

	public Employee userLogin(String username,String password) {	
		try (Connection conn = ConnectionDAO.connect()){
			String sql = "SELECT * FROM employee WHERE user_name=? AND pass_word=? AND administator=true;";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, username);
			psmt.setString(2, password);
			
			ResultSet rs = psmt.executeQuery();
	
			if(rs.next()) {
				e.setEmployeeID(rs.getInt("employee_id"));
	
				System.out.println(" Welcome Administator #" + e.getEmployeeID()+ "!");
				homePage();
				return e;
			}else {
				System.out.println("Username or password was incorrect");
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;		
	}
	
/*---------------------------------------------------------------------------------------------------------*/	

	public void customerInfo() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Customers \n 2. View Customer \n 3.Update Customer \n 4.Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			infoOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			//retry();
		}
	}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void infoOption(int choice) {
		switch(choice) {
			case 1: 
				getAllCustomers(); 
				homePage();
				break;
			case 2: 
				System.out.println("\n" + "-----------------------------------" + "\n");
				System.out.print("Please enter customer ID: ");
				int userID = sc.nextInt();
				as.getCustomerByID(userID); 
				homePage();
				break;
			case 3:
				updateCustomerByID();
				homePage(); 
				break;
			case 4: 
				homePage();
				break;
			default: 
				System.out.println("Invalid input"); 
				//retry();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	
		
	public void customerEdit() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println("  1. Update Account \n 2. Approve Account \n "
				+ " 3. Deny Account \n 4. Cancel Account \n 5. Exit");
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			editOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
			//retry();
		}
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public void editOption(int choice) {
		switch(choice) {
			case 1:
				updateAccountByID();
				homePage();
				break;
			case 2: 
				approveAccountByID();
				homePage();
				break;
			case 3: 
				denyAccountByID();
				homePage();
				break;
			case 4:
				cancelAccountByID();
				homePage(); 
				break;
			case 5:
				homePage();
				break;
			default: 
				System.out.println("Invalid input"); 
				//retry();
		}
	}
/*---------------------------------------------------------------------------------------------------------*/	
	//Optional - Transacation History
	public void customerTrans() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println("  1. View All Customer Transactions \n 2. View Customer Transaction \n "
				+ " 3.Deposit \n 4. Withdraw \n 5.Transfer \n 6. Exit" );
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
	//Optional - Transacation History
	public void transOption(int choice) {
		switch(choice) {
			case 1: 
				//getAllTransactions();
				homePage();
				break;
			case 2:
				//getTransaction();
				homePage();
				break;
			case 3:
				//deposit();
				homePage();
				break;
			case 4:
				//withdraw();
				homePage();
				break;
			case 5:
				//transfer();
				homePage();
				break;
			case 6: 
				homePage(); 
			default: 
				System.out.println("Invalid input"); 
				//retry();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	

}


