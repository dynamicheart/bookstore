package com.dynamicheart.bookstore.store.store.controller.order;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.store.controller.AbstractController;
import com.dynamicheart.bookstore.store.store.controller.HomeController;
import com.dynamicheart.bookstore.store.store.controller.customer.facade.CustomerFacade;
import com.dynamicheart.bookstore.store.store.controller.shoppingcart.facade.ShoppingCartFacade;
import com.dynamicheart.bookstore.store.store.model.AjaxResponse;
import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCartItem;
import org.apache.commons.lang3.Validate;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Controller
@RequestMapping("/store/order")
public class ShoppingOrderController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingOrderController.class);

    @Inject
    private OrderService orderService;

    @Inject
    private BookService bookService;

    @Inject
    private CustomerService customerService;

    @Inject
    private CustomerFacade customerFacade;

    @Inject
    private ShoppingCartFacade shoppingCartFacade;

    @RequestMapping("/single")
    @Transactional
    public ResponseEntity<AjaxResponse> singlePlacement(@NotEmpty @RequestParam(value = "bookIsbn") String bookIsbn,@NotEmpty @RequestParam(value = "quantity") int quantity, HttpServletRequest request, HttpServletResponse response) throws Exception{
        AjaxResponse ajaxResponse = new AjaxResponse();

        Book book = bookService.getByCode(bookIsbn);
        if(book == null || (book != null && quantity > book.getBookQuantity())){
            ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
            return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
        }

        OrderItem item = new OrderItem();
        item.setIsbn(bookIsbn);
        item.setItemName(book.getBookDescription().getName());
        item.setItemQuantity(quantity);
        item.setOneTimeCharge(book.getBookPrice());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        if(customer == null){
            ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
            ajaxResponse.setStatusMessage("Please log in");
            return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
        }

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(item);

        Date date = new Date();
        Order order = new Order();

        item.setOrder(order);

        order.setCustomerEmailAddress(customer.getEmailAddress());
        order.setCustomerId(customer.getId());
        order.setDatePurchased(date);
        order.setOrderDateFinished(date);
        order.setStatus(OrderStatus.ORDERED);
        order.setLastModified(date);
        order.setOrderItems(orderItems);

        BigDecimal total = orderService.calculateOrder(order);

        LOGGER.debug(total.toString());

        order.setTotal(total);


        orderService.saveOrUpdate(order);

        book.setBookQuantity(book.getBookQuantity() - quantity);
        bookService.save(book);

        ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
        return new ResponseEntity<AjaxResponse>(ajaxResponse,HttpStatus.OK);
    }

    @RequestMapping(value = "/checkout", consumes = "application/json")
    @Transactional
    public ResponseEntity<AjaxResponse> singlePlacement(@RequestBody List<String> bookIsbns, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        if(customer == null){
            ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_VALIDATION_FAILED);
            return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
        }

        if(bookIsbns.size() == 0){
            ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
            return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
        }

        ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute(StoreConstants.SHOPPING_CART);
        Validate.notNull(shoppingCart);

        Order order = new Order();
        Date date = new Date();
        Set<OrderItem> orderItems = new HashSet<>();
        for(String bookIsbn:bookIsbns){
            ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItems().remove(bookIsbn);
            OrderItem orderItem = new OrderItem();
            orderItem.setIsbn(shoppingCartItem.getBookIsbn());
            orderItem.setItemName(shoppingCartItem.getName());
            orderItem.setOneTimeCharge(shoppingCartItem.getBookPrice());
            orderItem.setOrder(order);
            orderItem.setItemQuantity(shoppingCartItem.getQuantity());
            orderItems.add(orderItem);
        }

        order.setDatePurchased(date);
        order.setOrderDateFinished(date);
        order.setLastModified(date);
        order.setCustomerId(customer.getId());
        order.setStatus(OrderStatus.ORDERED);
        order.setCustomerEmailAddress(customer.getEmailAddress());
        order.setOrderItems(orderItems);

        BigDecimal grandTotal = orderService.calculateOrder(order);
        order.setTotal(grandTotal);

        orderService.saveOrUpdate(order);

        BigDecimal shoppingCartTotal = shoppingCartFacade.calculateShoppingCart(shoppingCart);
        shoppingCart.setTotal(shoppingCartTotal);

        setSessionAttribute(StoreConstants.SHOPPING_CART,shoppingCart,request);

        ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
        return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
    }
}
