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
	
	public void updateAccount() {
		try {
			System.out.println("\n" + "-----------------------------------" + "\n");
			System.out.print(" Please enter customer ID: ");
			int userID = sc.nextInt();
			
			cs.getAccounts(userID);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print(" Please enter Account ID: ");
			int accID = sc.nextInt();
				
			System.out.println("\n ----------------------------------- \n");
			System.out.println(" 1. Account Name \n 2. Type \n 3. Approved \n 4. Exit");
			System.out.println("\n ----------------------------------- \n");
			int choice = sc.nextInt();
				switch(choice) {
				case 1: 
					updateAccountName(userID, accID);
					t1.run();
					break;
				case 2: 
					updateAccountType(userID, accID);
					t1.run();
					break;
				case 3: 
					updateAccountStatus(userID, accID);
					t1.run();
					break;
				default: 
					System.out.println(" Invalid Input");
					t1.run();
				}
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t1.run();
		}catch(Exception e1) {
			e1.printStackTrace();
			homePage();
		}
	}


/*---------------------------------------------------------------------------------------------------------*/	

	public boolean updateAccountName(int userID, int accID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql1 ="UPDATE account SET account_name=? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql1);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print(" Update Account Name: ");
			sc.nextLine();
			String accName = sc.nextLine();
			
			if(accName.equals("default")) {
				System.out.println(" Cannot change to 'default' account");
				t1.run();
			}else {
				psmt.setString(1, accName);
				psmt.setInt(2, userID);
				psmt.setInt(3, accID);
				psmt.execute();
				cs.getAccounts(userID);
				System.out.println();
				log.info("Account Name Updated");
				t1.run();
			}
			
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
	
	public boolean updateAccountType(int userID, int accID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql ="UPDATE account SET account_type=? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql);
			
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
				psmt.setString(1, "Savings");
				break;
			default: 
				System.out.println(" Invalid Input"); t1.run();
			}
			
			psmt.setInt(2, userID);
			psmt.setInt(3, accID);
			psmt.execute();
			log.info("Account Type Updated");
			cs.getAccounts(userID);
			t1.run();
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
	
	public boolean updateAccountStatus(int userID, int accID) {
		try(Connection conn = ConnectionDAO.connect()){
			String sql ="UPDATE account SET approved=? WHERE customer_fk=? AND account_id=?;";
			psmt = conn.prepareStatement(sql);
			
			System.out.println("\n ----------------------------------- \n");
			System.out.print(" Update Account Status \n\n");
			System.out.println(" 1. Deny \n 2. Approve \n 3. Exit");
			System.out.println("\n ----------------------------------- \n");
			int accStatus = sc.nextInt();
			switch(accStatus) {
			case 1:
				psmt.setBoolean(1, false);
				break;
			case 2:
				psmt.setBoolean(1, true);
				break;
			default: 
				System.out.println(" Invalid Input"); t1.run();
			}
			
			psmt.setInt(2, userID);
			psmt.setInt(3, accID);
			psmt.execute();
			System.out.println();
			log.info("Account Status Updated");
			cs.getAccounts(userID);
			t1.run();
				
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
	
	public void updateCustomer() {
		try(Connection conn = ConnectionDAO.connect()){
			System.out.println("\n ----------------------------------- \n");
			System.out.print(" Please enter customer ID: ");
			int userID = sc.nextInt();
			cs.getCustomer(userID);
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
				
				System.out.println();
				log.info("First Name Updated");
				break;
			case 2: 
				String sql2 ="UPDATE customer SET last_name=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql2);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Last Name: ");
				
				String lastName = sc.next();
				psmt.setString(1, lastName);
				
				System.out.println();
				log.info(" Last Name Updated");
				break;
			case 3: 
				String sql3 ="UPDATE customer SET email=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql3);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Email Address: ");
				
				String email = sc.next();
				psmt.setString(1, email);
				
				System.out.println();
				log.info("Email Updated");
				break;
			case 4: 
				String sql4 ="UPDATE customer SET phone_number=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql4);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update Phone Number: ");
				
				long phone = sc.nextLong();
				psmt.setLong(1, phone);
				
				System.out.println();
				log.info("Phone Number Updated");
				break;
			case 5: 
				String sql5 ="UPDATE customer SET pass_word=? WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql5);
				
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Update password: ");
				
				String password = sc.next();
				psmt.setString(1, password);
				
				System.out.println();
				log.info("Password Updated");
				break;
			case 6:
				homePage();
				break;
			default:
				System.out.println(" Invalid input");
				t1.run();
			}
			
			psmt.setInt(2, userID);
			psmt.execute();
			t1.run();
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
	}

/*---------------------------------------------------------------------------------------------------------*/	
	//If customer changed banks or broke rules of using account then cancel account
	// Any money in bank can either be transfer to new bank or a mailed check
	public void cancelAccount() {
		try {
		System.out.println("\n ----------------------------------- \n");
		System.out.print(" Please enter customer ID: ");
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
					String sql ="DELETE FROM account WHERE customer_fk=?; DELETE FROM transactions WHERE customer_fk=?; "
							+ "DELETE FROM customer WHERE customer_id=?;";
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, userID);
				psmt.setInt(2, userID);
				psmt.setInt(3, userID);
				
				psmt.execute();
				log.info("Account '" + userID + "' was cancelled");
				t1.start();
				}catch(SQLException e) {
					System.out.println(" Issue with SQL Connection: " + e);
					homePage();
				}catch(Exception e) {
					e.printStackTrace();
					homePage();
				}
				break;
			default:
				System.out.println("Invalid Input");
				t1.run();
				break;
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
//	Login and Options
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
			empLogin(username,password);
			
		}catch(InputMismatchException e) {
			System.out.println(" Invalid Input");
			sc.next();
			t2.run();
		}catch(SQLException e) {
			System.out.println(" Issue with SQL Connection: " + e);
			signin();
		}catch(Exception e) {
			e.printStackTrace();
			signin();
		}
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public Employee empLogin(String username,String password) {	
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

	public void customerInfo() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Customers \n 2. View Customer \n 3. Update Customer \n 4. Exit" );
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			infoOption(choice);
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input");
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
				System.out.print("Please enter customer ID: ");
				try {
					int userID = sc.nextInt();
					cs.getCustomer(userID); 
				}catch(InputMismatchException e) {
					System.out.println(" Invalid Input");
					sc.next();
					t1.run();
				}catch(Exception e) {
					e.printStackTrace();
					homePage();
				}
				homePage();
				break;
			case 3:
				updateCustomer();
				t1.run(); 
				break;
			case 4:
				homePage(); 
				break;
			default: 
				System.out.println(" Invalid input"); 
				t1.run();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	
		
	public void updateAccountOption() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. Update Account \n 2. Approve Account \n 3. Deny Account \n 4. Cancel Account \n 5. Exit");
		System.out.println("\n ----------------------------------- \n");
		
		try {
			int choice = sc.nextInt();
			updateOption(choice);
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

	public void updateOption(int choice) {
		switch(choice) {
			case 1:
				updateAccount();
				t1.run();
				break;
			case 2: 
				approveAccount();
				t1.run();
				break;
			case 3: 
				denyAccount();
				t1.run();
				break;
			case 4:
				cancelAccount();
				t1.run();
				break;
			default:
				System.out.println(" Invalid Input");
				t1.run();
		}
	}

/*---------------------------------------------------------------------------------------------------------*/	

	public void customerTrans() {
		System.out.println("\n ----------------------------------- \n");
		System.out.println(" 1. View All Customer Transactions \n 2. View Customer Transaction \n"
				+ " 3. Deposit \n 4. Withdraw \n 5. Transfer \n 6. Exit" );
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
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Please enter Customer ID: ");
				try {
				int userID = sc.nextInt();
				cs.getTransaction(userID);
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
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Please enter Customer ID: ");
				try {
				int userID2 = sc.nextInt();
				cs.deposit(userID2);
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
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Please enter Customer ID: ");
				try {
				int userID3 = sc.nextInt();
				cs.withdraw(userID3);
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
			case 5:
				System.out.println("\n ----------------------------------- \n");
				System.out.print(" Please enter Customer ID: ");
				try {
				int userID4 = sc.nextInt();
				cs.transfer(userID4);
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
			case 6: 
				homePage(); 
				break;
			default: 
				System.out.println(" Invalid input"); 
				t1.run();
		}
	} 
	
/*---------------------------------------------------------------------------------------------------------*/	

}


