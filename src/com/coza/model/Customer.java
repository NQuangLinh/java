package com.coza.model;

public class Customer {
	private int idCustomer;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String email;
	private String password;
	public Customer(int idCustomer, String fullName, String address, String phoneNumber, String email,
			String password) {
		this.idCustomer = idCustomer;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	public Customer(String fullName, String address, String phoneNumber, String email, String password) {
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
