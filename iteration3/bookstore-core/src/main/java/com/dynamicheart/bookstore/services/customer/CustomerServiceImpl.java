package com.dynamicheart.bookstore.services.customer;

import com.dynamicheart.bookstore.business.exception.ServiceException;
import com.dynamicheart.bookstore.model.customer.Customer;
import com.dynamicheart.bookstore.repositories.customer.CustomerRepository;
import com.dynamicheart.bookstore.services.common.generic.BookstoreEntityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public class CustomerServiceImpl extends BookstoreEntityServiceImpl<Long, Customer> implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;

    @Inject
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer getByNick(String nick) {
        return customerRepository.findByNick(nick);
    }

    @Override
    public void saveOrUpdate(Customer customer) throws ServiceException {
        LOGGER.debug("Creating Customer");

        if(customer.getId()!=null && customer.getId()>0) {
            super.update(customer);
        } else {

            super.create(customer);

        }
    }

    @Override
    public void delete(Customer customer) throws ServiceException {
        customerRepository.delete(customer);
    }
}
