package com.dynamicheart.bookstore.core.services.customer;

import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface CustomerService extends BookstoreEntityService<Long, Customer> {

    Customer getByNick(String nick);

    void saveOrUpdate(Customer customer) throws ServiceException;
}
