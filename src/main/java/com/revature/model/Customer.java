package com.revature.model;

public class Customer {
	private int userID;
	private String userName;
	private String passWord;
	
	String firstName;
	String lastName;
	
	String email;
	int number;
	
	public Customer() {
		userID = 0;
		userName = null;
		passWord = null;
		firstName = null;
		lastName = null;
		email = null;
		number = 0;
	}
	
	public Customer(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public Customer (int userID, String userName, String passWord,
	String firstName, String lastName, String email, int number) {
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.number = number;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
