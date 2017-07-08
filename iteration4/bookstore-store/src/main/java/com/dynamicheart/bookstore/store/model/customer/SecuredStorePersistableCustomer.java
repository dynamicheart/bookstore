package com.dynamicheart.bookstore.store.model.customer;

/**
 * Created by dynamicheart on 7/8/2017.
 */

public class SecuredStorePersistableCustomer extends SecuredCustomer{


    private static final long serialVersionUID = 7596993176981064209L;

    private String checkPassword;

    @Override
    public String getCheckPassword() {
        return checkPassword;
    }

    @Override
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
