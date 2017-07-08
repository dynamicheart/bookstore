package com.dynamicheart.bookstore.store.model.customer;

import com.dynamicheart.bookstore.store.model.Entity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class CustomerEntity extends Entity implements Serializable {

    private static final long serialVersionUID = -4024130272442044870L;

    @Email(message="{messages.invalid.email}")
    @NotEmpty(message="{NotEmpty.customer.emailAddress}")
    private String emailAddress;

    private String gender;

    private String language;

    private String encodedPassword = null;
    private String clearPassword = null;

    @NotEmpty(message="{NotEmpty.customer.userName}")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public String getClearPassword() {
        return clearPassword;
    }

    public void setClearPassword(String clearPassword) {
        this.clearPassword = clearPassword;
    }
}
