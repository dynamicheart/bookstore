package com.dynamicheart.bookstore.services.customer;

import com.dynamicheart.bookstore.business.exception.ServiceException;
import com.dynamicheart.bookstore.model.customer.Customer;
import com.dynamicheart.bookstore.services.common.generic.BookstoreEntityService;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface CustomerService extends BookstoreEntityService<Long, Customer> {

    Customer getByNick(String nick);

    void saveOrUpdate(Customer customer) throws ServiceException;
}
