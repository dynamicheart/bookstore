package com.dynamicheart.bookstore.store.store.controller.customer;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.store.store.controller.customer.facade.CustomerFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Controller
@RequestMapping("/store/customer/order")
public class CustomerOrdersController {

    @Inject
    private OrderService orderService;

    @Inject
    private CustomerFacade customerFacade;

    @PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping("/orders")
    public String displayOrders(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        List<Order> orders = null;

        if(customer != null){
            orders = orderService.listByCustomerId(customer.getId());
        }

        model.addAttribute("orders",orders);

        return "customer-orders";
    }

    @PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping("items")
    public String displayOrderItems(@RequestParam("orderId")Long orderId, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        Order order = orderService.findOne(orderId);

        Set<OrderItem> orderItems = order.getOrderItems();

        model.addAttribute("orderItems",orderItems);

        return "customer-order-items";
    }
}
