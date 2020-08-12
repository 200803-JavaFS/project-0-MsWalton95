package com.revature.model;

public class Employee {
	private int employeeID;
	private String userName;
	private String passWord;
	
	public Employee() {
		employeeID = 0;
		userName = null;
		passWord = null;
	}
	
	public Employee(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public Employee(int employeeID, String userName, String passWord) {
		super();
		this.employeeID = employeeID;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
