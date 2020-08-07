package com.revature.services;

import com.revature.model.Account;

public interface AccountDAO {
	public int withdraw(int money);
	public int deposit(int money);
	//public List<Account> viewAccountbyId();
	//public boolean createAccount(Account account);
}
