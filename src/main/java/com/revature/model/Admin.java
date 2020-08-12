package com.revature.model;

public class Admin {
	private int adminID;
	private String userName;
	private String passWord;
	
	public Admin() {

	}
	
	public Admin(int adminID, String userName, String passWord) {
		this.adminID = adminID;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
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
