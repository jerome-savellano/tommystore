package com.qbryx.tommystore.validator;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;

public class RegisterUser {
	
	private String email;
	
	private String password;
	
	private String confirmPassword;
	
	private String firstName;
	
	private String lastName;
	
	private String contactNumber;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public User buildCustomer(){
		
		User user = new User();
		
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setUserType(UserType.CUSTOMER);
		user.setContactNumber(contactNumber);
		
		return user;
	}
	
	public User buildAdministrator(){
		
		User user = new User();
		
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setUserType(UserType.ADMINISTRATOR);
		user.setContactNumber(contactNumber);
		
		return user;
	}
}
