package com.revature.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.revature.model.Admin;
import com.revature.model.Employee;
import com.revature.utility.ConnectionDAO;

public class AdminService extends EmployeeService {
	Admin a = new Admin();
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void cancelAccountByID() {}
	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login() {
		System.out.println("\n ----------------------------------- \n");
		try(Connection conn = ConnectionDAO.connect()) {
			System.out.println("ADMINISTRATOR LOGIN \n");
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
				//cs.updateCustomer();
				homePage(); 
				break;
			case 4: 
				homePage();
				break;
			default: System.out.println("Invalid input"); retry();
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
				updateAccountByID(userID);
				homePage();
				break;
			
			case 2: 
				approveAccountByID(userID);
				homePage();
				break;
			case 3: 
				denyAccountByID(userID);
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
				retry();
		}
	}
/*---------------------------------------------------------------------------------------------------------*/	
	
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
				retry();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	

}


