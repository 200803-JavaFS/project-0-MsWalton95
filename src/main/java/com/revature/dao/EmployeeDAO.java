package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface EmployeeDAO {
	public List<Customer> getAllCustomers();
	
	public List<Account> getAllAccounts();
	
	public List<Account> getAllPendingAccounts();
	
	public List<Account> getAllOpenAccounts();
	
	public boolean denyAccountByID(int userID);
	
	public boolean approveAccountByID(int userID);
}
