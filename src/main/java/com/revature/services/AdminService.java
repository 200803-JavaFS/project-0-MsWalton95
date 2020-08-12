package com.revature.services;

import java.util.InputMismatchException;

import com.revature.model.Admin;

public class AdminService extends EmployeeService {
	Admin a = new Admin();
	
	public void login() {
		super.login();
	}
	
	public void signin() {
		super.signin();
		System.out.println("Welcome" + a.getUserName() + "!");
		option();
	}
	
	public void option() {
		super.option();
	}
	
	public void approveAccount() {}
	
	public void denyAccount() {}
	
	public void cancelAccount() {}
	
}


