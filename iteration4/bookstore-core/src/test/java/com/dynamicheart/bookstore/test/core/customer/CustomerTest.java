package com.dynamicheart.bookstore.test.core.customer;

import com.dynamicheart.bookstore.core.model.statistics.Statistics;
import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.test.core.common.AbstractBookstoreCoreTestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
        customer.setEmailAddress("test@test.com");
        customer.setGender(CustomerGender.M);

        customerService.create(customer);

    }

}
