package com.dynamicheart.bookstore.store.store.controller.customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Controller
@RequestMapping("/store/customer")
public class CustomerDashboardController {

    @PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping(value="/dashboard", method= RequestMethod.GET)
    public String displayCustomerDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "dashboard";

    }
}
