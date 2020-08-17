package com.revature.dao;

public interface CustomerDAO {
	
	public boolean registerLogin();
	
	public boolean createAccount();
	
	public boolean createAccountInfo(int userID, String name);
	
	public void updateCustomer(int userID);
}
