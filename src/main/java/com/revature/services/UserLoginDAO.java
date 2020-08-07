package com.revature.services;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.UserLogin;

public interface UserLoginDAO {
	public boolean createAccount(UserLogin user, Customer customer, Account account); //Create an user account for an existing bank account 
	public boolean logIn(UserLogin user); 
	public void logOut();
	public void loginTitle();
	public void loginOption();
}
