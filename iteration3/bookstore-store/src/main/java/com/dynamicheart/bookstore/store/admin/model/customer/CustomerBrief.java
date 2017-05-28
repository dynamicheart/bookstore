package com.dynamicheart.bookstore.store.admin.model.customer;

import com.dynamicheart.bookstore.core.model.customer.CustomerGender;

import java.io.Serializable;

/**
 * Created by dynamicheart on 5/28/2017.
 */
public class CustomerBrief implements Serializable{

    private static final long serialVersionUID = -8533412756833360236L;

    private Long id;

    private String nick;

    private CustomerGender gender;

    private String emailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public CustomerGender getGender() {
        return gender;
    }

    public void setGender(CustomerGender gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
