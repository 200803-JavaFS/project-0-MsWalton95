package com.revature.dao;

import com.revature.model.Employee;

public interface AdminDAO {
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Main Administrator Services
/*---------------------------------------------------------------------------------------------------------*/	
			
	public void updateAccount();
	
	public boolean updateAccountName(int userID, int accID);
	
	public boolean updateAccountType(int userID, int accID);
	
	public boolean updateAccountStatus(int userID, int accID);
	
	public void updateCustomer();
	
	public void cancelAccount();
	
/*---------------------------------------------------------------------------------------------------------*/	
//	Login and Options
/*---------------------------------------------------------------------------------------------------------*/	
	public void signin();
	
	public void login();
	
	public Employee empLogin(String username,String password);
	
	public void customerInfo();
	
	public void infoOption(int choice);
	
	public void updateAccountOption();
	
	public void updateOption(int choice);
	
	public void customerTrans();
	
	public void transOption(int choice);

}
