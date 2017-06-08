package com.dynamicheart.bookstore.store.admin.controller.orders;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.common.Billing;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.store.admin.model.order.Order;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import com.dynamicheart.bookstore.store.utils.DateUtil;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by dynamicheart on 5/29/2017.
 */

@Controller
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private LabelUtils messages;

    @Inject
    private BookService bookService;

    @Inject
    private OrderService orderService;

    @Inject
    private CustomerService customerService;


    @RequestMapping(value="/admin/order/detail", method= RequestMethod.GET)
    public String displayOrderEdit(@RequestParam("id") long orderId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayOrder(orderId,model,request,response);

    }

    @RequestMapping(value="/admin/order/create", method= RequestMethod.GET)
    public String displayOrderCreate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayOrder(null,model,request,response);

    }


    @RequestMapping(value="/admin/item/create", method= RequestMethod.GET)
    public String displayItemrCreate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        this.setMenu(model, request);

        List<Book> books = bookService.list();
        List<com.dynamicheart.bookstore.core.model.order.Order> orders = orderService.list();

        OrderItem orderItem = new OrderItem();

        model.addAttribute("orderItem",orderItem);
        model.addAttribute("books",books);
        model.addAttribute("orders",orders);
        return "admin-item";

    }

    private String displayOrder(Long orderId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //display menu
        setMenu(model,request);

        Order order = new Order();

        List<Customer> customers = customerService.list();

        if(orderId!=null && orderId!=0) {		//edit mode

            Set<OrderItem> orderItems = null;

            com.dynamicheart.bookstore.core.model.order.Order dbOrder = orderService.getById(orderId);

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

        }else{
            order.setDatePurchased(DateUtil.formatDate(new Date()));
            order.setBilling(new Billing());
            order.setOrder(new com.dynamicheart.bookstore.core.model.order.Order());
        }

        model.addAttribute("order",order);
        model.addAttribute("customers",customers);
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


    @RequestMapping(value="/admin/order/save", method=RequestMethod.POST)
    public String saveOrder(@Valid @ModelAttribute("order") com.dynamicheart.bookstore.store.admin.model.order.Order entityOrder, BindingResult result, Model model, HttpServletRequest request) throws Exception {

        //set the id if fails
        entityOrder.setId(entityOrder.getOrder().getId());

        List<Customer> customers = customerService.list();

        model.addAttribute("order", entityOrder);


        if(entityOrder.getOrder().getId() != null && entityOrder.getOrder().getId() != 0) {


            Set<OrderItem> orderItems = new HashSet<OrderItem>();

            com.dynamicheart.bookstore.core.model.order.Order newOrder = orderService.getById(entityOrder.getOrder().getId());


            if (result.hasErrors()) {
                //  somehow we lose data, so reset Order detail info.
                entityOrder.getOrder().setOrderItems(orderItems);

                return "admin-order";
		/*	"admin-orders-edit";  */
            }

            newOrder.setBilling(entityOrder.getOrder().getBilling());
            newOrder.setCustomerId(entityOrder.getOrder().getCustomerId());
            newOrder.setStatus(entityOrder.getOrder().getStatus());
            newOrder.setCustomerEmailAddress("213123@qq.com");
            newOrder.setDatePurchased(new Date());
            newOrder.setLastModified(new Date());

            orderService.saveOrUpdate(newOrder);
            entityOrder.setOrder(newOrder);
            entityOrder.setBilling(newOrder.getBilling());
            model.addAttribute("order", entityOrder);

            Long customerId = newOrder.getCustomerId();
        }else{
            com.dynamicheart.bookstore.core.model.order.Order newOrder = new com.dynamicheart.bookstore.core.model.order.Order();
            newOrder.setStatus(entityOrder.getOrder().getStatus());
            newOrder.setBilling(entityOrder.getOrder().getBilling());
            newOrder.setCustomerId(entityOrder.getOrder().getCustomerId());
            newOrder.setCustomerEmailAddress("213123@qq.com");
            newOrder.setDatePurchased(new Date());
            newOrder.setLastModified(new Date());
            orderService.create(newOrder);
            entityOrder.setOrder(newOrder);
            entityOrder.setBilling(newOrder.getBilling());
            model.addAttribute("order", entityOrder);
        }

        model.addAttribute("success","success");
        model.addAttribute("customers",customers);

        return  "admin-order";
	    /*	"admin-orders-edit";  */
    }
}
