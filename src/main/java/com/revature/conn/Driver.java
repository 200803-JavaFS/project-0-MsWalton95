package com.revature.conn;

import com.revature.services.UserLoginDAO;
import com.revature.services.UserLoginDAOImpl;

public class Driver {

	public static void main(String[] args) {
		UserLoginDAO ul = new UserLoginDAOImpl();
		ul.loginTitle();
		ul.loginOption();
		
	}
}
