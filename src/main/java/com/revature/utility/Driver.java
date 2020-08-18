package com.revature.utility;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.*;
import com.revature.services.*;

public class Driver {

	public static void main(String[] args) throws SQLException{
		CustomerService cs = new CustomerService();
		EmployeeService es = new EmployeeService();
		AdminService as = new AdminService();
		ConsoleDriver cd = new ConsoleDriver();
		
		//Customer -> Personal Information -A
		//Customer -> Transaction - fix the details to deposit/transfer/withdraw - Limit to 5 when seeing transaction
		cd.begin();
	}
}
