package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Transaction;

public interface CustomerDAO {
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Transactions
/*---------------------------------------------------------------------------------------------------------*/	

	public boolean withdraw(int userID);
	
	public boolean deposit(int userID);
	
	public boolean transfer(int userID);
	
	public boolean getTotal(int userID,String accType, double money);
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Retrieve Information
/*---------------------------------------------------------------------------------------------------------*/	

	public Customer getCustomer(int userID);
	
	public Account getAccount(int userID, int accID);
	
	public List<Transaction> getRecentTransaction(int userID);
	
	public List<Transaction> getTransaction(int userID);
	
	public List<Account> getAccounts(int userID);
	
	public List<Account> getPendingAccounts(int userID);
	
	public List<Account> getOpenAccounts(int userID);
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Main Customer Services
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void updateCustomer(int userID);
	
	public boolean registerLogin();
	
	public boolean createAccount();
	
	public boolean createAccountInfo(int userID, String fullName, String username, String password);

/*---------------------------------------------------------------------------------------------------------*/	
//	Login and Options
/*---------------------------------------------------------------------------------------------------------*/	
	
	public void signin();
	
	public void login();
	
	Customer userLogin(String username,String password);
	
	public void homePage();
	
	public void homeOption(int choice);
	
	public void infoOption();
	
	public void transOption();
	
	public void logout();

}
