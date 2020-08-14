package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface AccountDAO {

	public Customer getCustomerByID();
	
	public List<Account> getAccountByID();
	
	public List<Account> getOpenAccountsByID();
	
	public List<Account> getPendingAccountsByID();
	
	public boolean createAccountByID();
	
	public boolean deleteAccountByID();
	
}
