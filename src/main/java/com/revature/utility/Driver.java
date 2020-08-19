package com.revature.utility;

import com.revature.services.*;

public class Driver {

	public static void main(String[] args){
		CustomerService cs = new CustomerService();
		EmployeeService es = new EmployeeService();
		AdminService as = new AdminService();
		ConsoleDriver cd = new ConsoleDriver();
		
		es.getAllAccounts();
//		cd.begin();
		
		
	}
}




