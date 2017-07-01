package com.dynamicheart.bookstore.core.model.common;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Delivery {
	
	@Column(name ="DELIVERY_LAST_NAME", length=64)
	private String lastName;

	@Column(name ="DELIVERY_FIRST_NAME", length=64)
	private String firstName;

	@Column(name ="DELIVERY_STATE", length=100)
	private String province;

	@Column(name ="DELIVERY_CITY", length=100)
	private String city;

	@Column(name ="DELIVERY_STREET_ADDRESS", length=256)
	private String address;
	
	@Column(name ="DELIVERY_POSTCODE", length=20)
	private String postalCode;
	
	@Column(name="DELIVERY_TELEPHONE", length=32)
	private String phone;

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
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
