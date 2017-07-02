package com.dynamicheart.bookstore.test.core.customer;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.test.core.common.AbstractBookstoreCoreTestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

/**
 * Created by dynamicheart on 5/23/2017.
 */

@Ignore
public class CustomerTest extends AbstractBookstoreCoreTestCase {

    @Test
    public void createCustomer() throws ServiceException{

        Customer customer = new Customer();
        customer.setNick("My nick");
        customer.setPassword("123456");
        customer.setDateOfBirth(new Date());
        customer.setEmailAddress("test@test.com");
        customer.setGender(CustomerGender.M);

        customerService.create(customer);
    }

}
