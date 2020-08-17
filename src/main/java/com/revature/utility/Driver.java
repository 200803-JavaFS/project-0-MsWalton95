package com.revature.utility;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.*;
import com.revature.services.*;

public class Driver {

	public static void main(String[] args) throws SQLException{
		CustomerService cs = new CustomerService();
		AccountService as = new AccountService();
		EmployeeService es = new EmployeeService();
		AdminService ads = new AdminService();
		ConsoleDriver cd = new ConsoleDriver();
		
		//cd.begin();
		
//		System.out.println("-------------------------All Customers------------------------------");
//		es.getAllCustomers();
//		System.out.println("-------------------------All Accounts------------------------------");
		//es.getAllAccounts();
//		System.out.println("-------------------------All Pending Accounts------------------------------");
//		es.getAllPendingAccounts();
//		System.out.println("-------------------------All Open Accounts------------------------------");
//		es.getAllOpenAccounts();
//		System.out.println("-------------------------Deny Accounts------------------------------");		
//		es.denyAccountByID();
//		System.out.println("-------------------------Approve Accounts------------------------------");
//		es.approveAccountByID();
		
//		System.out.println("-------------------------Customer By ID------------------------------");
//		as.getCustomerByID();
//		System.out.println("-------------------------Account By ID------------------------------");
//		as.getAccountByID();
//		System.out.println("-------------------------Open Account By ID------------------------------");
//		as.getOpenAccountsByID();
//		System.out.println("-------------------------Pending Account By ID------------------------------");
//		as.getPendingAccountsByID();		
//		System.out.println("-------------------------Create Account By ID------------------------------");
//		as.createAccountByID();
//		System.out.println("-------------------------Delete Account By ID------------------------------");
//		as.deleteAccountByID();
		
		//cs.updateCustomer(4);
		//ads.updateCustomer(5);
		//es.denyAccountByID(4);
//		ads.updateAccountByID(4);
//		as.deposit(4);
		//as.withdraw(4);
		as.transferFunds(4);
		//ads.cancelAccountByID();
	}
}
