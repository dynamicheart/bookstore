package com.dynamicheart.bookstore.store.store.controller.customer.facade;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.store.store.model.customer.CustomerEntity;
import com.dynamicheart.bookstore.store.store.model.customer.ReadableAndPersistableCustomer;

/**
 * Created by dynamicheart on 7/6/2017.
 */
public interface CustomerFacade {

    public CustomerEntity getCustomerDataByUserName(final String userName,final Language language) throws Exception;

    public ReadableAndPersistableCustomer getCustomerById(final Long id, final Language language) throws Exception;

    public Customer getCustomerByUserName(final String userName) throws Exception;

    public boolean checkIfUserExists(final String userName) throws Exception;

    public CustomerEntity  registerCustomer( final ReadableAndPersistableCustomer customer, final Language language) throws Exception;

    public void setCustomerModelDefaultProperties(Customer customer) throws Exception;

    public void authenticate(Customer customer, String userName, String password) throws Exception;

    Customer getCustomerModel(ReadableAndPersistableCustomer customer, Language language) throws Exception;

    Customer populateCustomerModel(Customer customerModel, ReadableAndPersistableCustomer customer, Language language) throws Exception;



}
