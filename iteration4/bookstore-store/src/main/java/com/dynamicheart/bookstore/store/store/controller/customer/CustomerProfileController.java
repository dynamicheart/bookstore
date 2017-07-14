package com.dynamicheart.bookstore.store.store.controller.customer;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.store.common.constants.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dynamicheart on 7/14/2017.
 */

@Controller
@RequestMapping("/store/customer")
public class CustomerProfileController {

    @PreAuthorize("has_role('AUTH_CUSTOMER')")
    @RequestMapping("/profile")
    public String displayProfile(Model model, HttpServletRequest request, HttpServletResponse response){

        Customer customer = (Customer)request.getAttribute(Constants.CUSTOMER);

        return null;

    }
}
