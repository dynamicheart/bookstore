package com.dynamicheart.bookstore.store.admin.controller.customers.rest;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 5/26/2017.
 */

@RestController
public class CustomerRESTController {

    @Inject
    private CustomerService customerService;

    @RequestMapping(
            value = "admin/api/customers",
            params = {"page","size"},
            method = RequestMethod.GET
    )
    public Page<Customer> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size){
        return customerService.findPaginated(page, size);
    }
}
