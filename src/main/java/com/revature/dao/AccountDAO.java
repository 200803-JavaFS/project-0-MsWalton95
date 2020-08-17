package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface AccountDAO {

	public Customer getCustomerByID(int userID);
	
	public List<Account> getAccountByID(int userID);
	
	public List<Account> getOpenAccountsByID(int userID);
	
	public List<Account> getPendingAccountsByID(int userID);
	
}
