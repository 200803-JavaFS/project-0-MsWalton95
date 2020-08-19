package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.Transaction;

public interface EmployeeDAO {
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Update Pending Accounts	
/*---------------------------------------------------------------------------------------------------------*/	
	
	public boolean denyAccount();
	
	public boolean approveAccount();
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Retrieve Information
/*---------------------------------------------------------------------------------------------------------*/	
	public List<Transaction> getAllTransactions();
	
	public List<Customer> getAllCustomers();
	
	public List<Account> getAllAccounts();
	
	public List<Account> getAllPendingAccounts();
	
	public List<Account> getAllOpenAccounts();	
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Login and Options
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void login();
	
	public Employee empLogin(String username,String password);
	
	public void homePage();
	
	public void homeOption(int choice);
	
	public void customerInfo();
	
	public void infoOption(int choice);
	
	public void customerAccount();
	
	public void accountOption(int choice);
	
	public void getAllAccountOption();
	
	public void getAccountOption();
	
	public void updateAccountOption();
	
	public void customerEdit();
	
	public void editOption(int choice);
	
	public void customerTrans();
	
	public void transOption(int choice);
	
}
