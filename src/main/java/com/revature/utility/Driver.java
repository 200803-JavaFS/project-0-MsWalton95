package com.revature.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.revature.services.*;

public class Driver {

	public static void main(String[] args){
		CustomerService cs = new CustomerService();
		EmployeeService es = new EmployeeService();
		AdminService as = new AdminService();
		ConsoleDriver cd = new ConsoleDriver();
		
		//cs.withdraw(2);
		cs.deposit(2);
//		cs.transfer(2);
//		cd.begin();
		
		
	}
}

//public boolean withdraw(int userID) {
//	try(Connection conn = ConnectionDAO.connect()){
//		getOpenAccounts(userID);
//		
//		System.out.println("\n ----------------------------------- \n");
//		System.out.print(" Which Account ID to withdraw from? ");
//		int accID = sc.nextInt();
//
//		getAccount(userID, accID); 
//		double balance = a.getBalance();
//		System.out.println("\n ----------------------------------- \n");
//		System.out.print(" Enter the amount to withdraw: ");
//		int money = sc.nextInt();
//	
//		String sql = "UPDATE account SET balance=balance-? WHERE customer_fk=? AND account_id=?;";
//		psmt = conn.prepareStatement(sql);
//		
//		if(money > balance) {
//			System.out.println("\n Withdrawn cannot be more than balance");
//			t1.run();
//		}else if(money > 1000) {
//			System.out.println("\n Withdrawn cannot be more than 1000 per day");
//			t1.run();
//		}else if(money <= 0) {
//			System.out.println("\n Withdrawn cannot be a negative value");
//			t1.run();
//		}else {
//			psmt.setInt(1, money);
//			psmt.setInt(2, userID);
//			psmt.setInt(3, accID);
//			
//			psmt.execute();
//			getTotal(userID ,"Withdraw", money);
//			System.out.println();
//			log.info("Withdrawn complete");
//			
//			getAccounts(userID);
//			return true;
//			}
//	}catch(InputMismatchException e) {
//		System.out.println("Invalid Input");
//		sc.next();
//		t1.run();
//	}catch(SQLException e) {
//		System.out.println("Issue with SQL Connection: " + e);
//		homePage();
//	}catch(Exception e) {
//		e.printStackTrace();
//		homePage();
//	}
//	return false;
//}


