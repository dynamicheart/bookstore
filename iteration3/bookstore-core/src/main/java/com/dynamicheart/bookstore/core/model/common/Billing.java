package com.dynamicheart.bookstore.core.model.common;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Embeddable
public class Billing {
	
	@NotEmpty
	@Column(name ="BILLING_LAST_NAME", length=64, nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name ="BILLING_FIRST_NAME", length=64, nullable=false)
	private String firstName;


	@Column(name ="BILLING_COMPANY", length=100)
	private String company;
	
	@Column(name ="BILLING_STREET_ADDRESS", length=256)
	private String address;
	
	
	@Column(name ="BILLING_CITY", length=100)
	private String city;
	
	@Column(name ="BILLING_POSTCODE", length=20)
	private String postalCode;
	
	@Column(name="BILLING_TELEPHONE", length=32)
	private String telephone;
	
	@Column(name ="BILLING_STATE", length=100)
	private String state;


	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}
	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}