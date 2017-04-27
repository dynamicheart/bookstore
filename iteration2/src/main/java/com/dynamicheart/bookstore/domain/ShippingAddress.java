package com.dynamicheart.bookstore.domain;

import com.dynamicheart.bookstore.domain.enumtype.City;
import com.dynamicheart.bookstore.domain.enumtype.Province;
import com.dynamicheart.bookstore.domain.enumtype.Street;
import com.dynamicheart.bookstore.domain.enumtype.Zipcode;

/**
 * Created by dynamicheart on 4/18/2017.
 */
public class ShippingAddress {
    private long id;
    private long user_id;
    private Province province;
    private int PhoneNumber;
    private City city;
    private Street street;
    private Zipcode zipcode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Zipcode getZipcode() {
        return zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }
}
