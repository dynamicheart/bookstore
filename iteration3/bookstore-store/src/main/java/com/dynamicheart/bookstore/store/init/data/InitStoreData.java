package com.dynamicheart.bookstore.store.init.data;

import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by dynamicheart on 5/28/2017.
 */
public class InitStoreData implements InitData{

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CustomerService customerService;

    @Override
    public void initInitialData() throws ServiceException {

        if(customerService.count() == 0){
            Customer customer = new Customer();
            customer.setNick("test");
            customer.setGender(CustomerGender.M);
            customer.setDateOfBirth(new Date());
            customer.setEmailAddress("test@bookstore.com");
            String password = passwordEncoder.encode("password");
            customer.setPassword(password);
            customerService.create(customer);
        }
    }
}
