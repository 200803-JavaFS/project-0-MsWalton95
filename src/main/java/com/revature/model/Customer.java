package com.revature.model;

public class Customer {
	private int customerID;
	private String firstName, lastName;
	private String street, city, state;
	private int zipcode;
	private String email;
	private int phoneNumber;
	
	public Customer() {
		customerID = 0;
		firstName = null;
		lastName = null;
		street = null;
		city = null;
		state = null;
		zipcode = 0;
		email = null;
		phoneNumber = 0;
	}

	public Customer(int customerID, String firstName, String lastName, 
			String street, String city, String state, int zipcode, 
			String email, int phoneNumber) 
	{
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
