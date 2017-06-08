package com.dynamicheart.bookstore.store.admin.controller.orders;


import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dynamicheart on 5/29/2017.
 */

@Controller
public class OrdersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);
    
    @Inject
    private OrderService orderService;

    /**
     * List of orders
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/orders", method= RequestMethod.GET)
    public String displayOrders(Model model, HttpServletRequest request) throws Exception {

        this.setMenu(model, request);

        return "admin-orders";
    }

    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("Order", "Order");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");

        model.addAttribute("activeMenus",activeMenus);
    }
}
