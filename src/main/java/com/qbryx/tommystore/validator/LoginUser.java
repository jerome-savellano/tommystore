package com.qbryx.tommystore.validator;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginUser {
	
	@Email(message="Please enter a valid email address")
	@NotEmpty(message="Please enter your email address")
	private String email;
	
	@NotEmpty(message="Please enter your password")
	private String password;

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
