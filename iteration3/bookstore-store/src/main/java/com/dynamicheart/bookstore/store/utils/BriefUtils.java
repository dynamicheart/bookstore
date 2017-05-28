package com.dynamicheart.bookstore.store.utils;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.store.admin.model.customer.CustomerBrief;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dynamicheart on 5/28/2017.
 */
public class BriefUtils {

    public static List<CustomerBrief> getCustomerBriefs(List<Customer> customers){
        List<CustomerBrief> customerBriefs = new LinkedList<>();

        for (Customer customer: customers) {
            CustomerBrief customerBrief = new CustomerBrief();
            customerBrief.setId(customer.getId());
            customerBrief.setNick(customer.getNick());
            customerBrief.setGender(customer.getGender());
            customerBrief.setEmailAddress(customer.getEmailAddress());
            customerBriefs.add(customerBrief);
        }

        return customerBriefs;
    }
}
