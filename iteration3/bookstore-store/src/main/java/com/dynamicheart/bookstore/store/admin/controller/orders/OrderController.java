package com.dynamicheart.bookstore.store.admin.controller.orders;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import com.dynamicheart.bookstore.store.utils.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dynamicheart on 5/29/2017.
 */

@Controller
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderService orderService;

    @Inject
    private CustomerService customerService;


    @RequestMapping(value="/admin/order", method= RequestMethod.GET)
    public String displayOrderEdit(@RequestParam("id") long orderId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayOrder(orderId,model,request,response);

    }

    private String displayOrder(Long orderId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //display menu
        setMenu(model,request);

        com.dynamicheart.bookstore.store.admin.model.order.Order order = new com.dynamicheart.bookstore.store.admin.model.order.Order();

        if(orderId!=null && orderId!=0) {		//edit mode

            Set<OrderItem> orderItems = null;

            Order dbOrder = orderService.getById(orderId);

            if(dbOrder==null) {
                return "redirect:/admin/orders";
            }


            order.setId( orderId );

            if( dbOrder.getDatePurchased() !=null ){
                order.setDatePurchased(DateUtil.formatDate(dbOrder.getDatePurchased()));
            }

            Long customerId = dbOrder.getCustomerId();

            if(customerId!=null && customerId>0) {

                try {

                    Customer customer = customerService.getById(customerId);
                    if(customer!=null) {
                        model.addAttribute("customer",customer);
                    }


                } catch(Exception e) {
                    LOGGER.error("Error while getting customer for customerId " + customerId, e);
                }

            }

            order.setOrder( dbOrder );
            order.setBilling( dbOrder.getBilling() );

            orderItems = dbOrder.getOrderItems();

        }

        model.addAttribute("order",order);
        return  "admin-order";
    }


    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("0rder", "0rder");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");


        model.addAttribute("activeMenus",activeMenus);
        //

    }
}
