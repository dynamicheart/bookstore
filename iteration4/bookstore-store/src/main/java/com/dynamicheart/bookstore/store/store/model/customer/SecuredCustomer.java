package com.dynamicheart.bookstore.store.store.model.customer;

import com.dynamicheart.bookstore.store.utils.FieldMatch;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */

@FieldMatch.List({
        @FieldMatch(first="password",second="checkPassword",message="password.notequal")

})
public class SecuredCustomer extends ReadableAndPersistableCustomer implements Serializable {


    private static final long serialVersionUID = -4308256821330962664L;

    @Size(min=6, message="User name must be at least 6 characters long")
    private String password;

    @Size(min=6, message="User name must be at least 6 characters long")
    private String checkPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
