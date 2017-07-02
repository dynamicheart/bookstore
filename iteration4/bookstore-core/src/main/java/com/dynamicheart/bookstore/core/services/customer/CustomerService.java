package com.dynamicheart.bookstore.core.services.customer;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface CustomerService extends BookstoreEntityService<Long, Customer> {

    Customer getByNick(String nick);

    void saveOrUpdate(Customer customer) throws ServiceException;
}
