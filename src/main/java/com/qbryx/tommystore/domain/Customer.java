package com.qbryx.tommystore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qbryx.tommystore.enums.Country;

@Entity
@Table(name="CUSTOMER")
public class Customer {
	
	private long id;
	
	private User user;
	
	private String address1;
	
	private String address2;
	
	private String zipCode;
	
	private Country country;
	
	private String creditCardNumber;
	
	private String creditSecurityCode;
	
	public Customer(){
		
	}

	public Customer(long id, User user, String address1, String address2, String zipCode, Country country,
			String creditCardNumber, String creditSecurityCode) {
		super();
		this.id = id;
		this.user = user;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.country = country;
		this.creditCardNumber = creditCardNumber;
		this.creditSecurityCode = creditSecurityCode;
	}

	@Id
	@GeneratedValue
	@Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="address_1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name="address_2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name="zip_code")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="country")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name="credit_card_number")
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Column(name="credit_security_code")
	public String getCreditSecurityCode() {
		return creditSecurityCode;
	}

	public void setCreditSecurityCode(String creditSecurityCode) {
		this.creditSecurityCode = creditSecurityCode;
	}
}
