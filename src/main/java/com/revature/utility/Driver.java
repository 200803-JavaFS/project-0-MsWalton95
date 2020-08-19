package com.revature.utility;

import com.revature.dao.CustomerDAO;
import com.revature.services.CustomerService;

public class Driver {

	public static void main(String[] args){
		ConsoleDriver cd = new ConsoleDriver();
		CustomerDAO cs = new CustomerService();
		cs.createAccount();
		
		//cd.begin();
	}
}
