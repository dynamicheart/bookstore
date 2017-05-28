package com.dynamicheart.bookstore.store.admin.controller.customers.rest;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dynamicheart on 5/26/2017.
 */

@RestController
public class CustomerRESTController {

    @Inject
    private CustomerService customerService;

    @RequestMapping(
            value = "api/admin/customers",
            params = {"draw", "start","length"},
            method = RequestMethod.GET
    )
    public @ResponseBody Map<String, Object> findPaginated(
            @RequestParam("draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length){
        Map<String, Object> result = new HashMap<>();
        Page<Customer> pages = customerService.findPaginated(start/length, length);
        result.put("data",pages.getContent());
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotalElements());
        result.put("recordsFiltered",pages.getTotalElements());
        return result;
    }
}