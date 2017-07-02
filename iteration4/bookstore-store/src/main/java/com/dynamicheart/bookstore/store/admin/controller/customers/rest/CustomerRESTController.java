package com.dynamicheart.bookstore.store.admin.controller.customers.rest;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.dynamicheart.bookstore.store.utils.BriefUtils.getCustomerBriefs;

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
    public @ResponseBody Map<String, Object> pageCustomers(
            @RequestParam("draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length){
        Map<String, Object> result = new HashMap<>();
        Page<Customer> pages = customerService.findPaginated(start/length, length);
        result.put("data", getCustomerBriefs(pages.getContent()));
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotalElements());
        result.put("recordsFiltered",pages.getTotalElements());
        return result;
    }

    @RequestMapping(
            value="api/admin/customer/{id}",
            method=RequestMethod.DELETE
    )
    public @ResponseBody ResponseEntity<String> deleteCustomer(
            @PathVariable("id") Long id){
        try {
            Customer customer = customerService.getById(id);

            if(customer == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            } else {
                customerService.delete(customer);
                return new ResponseEntity<String>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
