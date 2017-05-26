package com.dynamicheart.bookstore.store.admin.controller.customers.rest;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by dynamicheart on 5/26/2017.
 */

@RestController
@RequestMapping(value = "/admin/api")
public class CustomerRestConstroller {

    @Inject
    private CustomerService customerService;

    @JsonView(DataTablesOutput.View.class)
    @RequestMapping(value = "/data/users", method = RequestMethod.GET)
    public DataTablesOutput<Customer> getCustomers(@Valid DataTablesInput input) {
        return customerService.findAll(input);
    }

}
