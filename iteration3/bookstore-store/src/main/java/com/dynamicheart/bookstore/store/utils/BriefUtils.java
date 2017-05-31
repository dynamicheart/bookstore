package com.dynamicheart.bookstore.store.utils;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.common.Description;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.store.admin.model.catalog.book.BookBrief;
import com.dynamicheart.bookstore.store.admin.model.customer.CustomerBrief;
import com.dynamicheart.bookstore.store.admin.model.order.OrderBrief;

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


    public static List<BookBrief> getBookBriefs(List<Book> books){
        List<BookBrief> bookBriefs = new LinkedList<>();

        for(Book book: books){
            BookBrief bookBrief = new BookBrief();
            bookBrief.setId(book.getId());
            bookBrief.setIsbn(book.getIsbn());
            bookBrief.setAvailable(book.isAvailable());

            bookBrief.setTitle(book.getDescriptions().iterator().next().getName());
            bookBriefs.add(bookBrief);
        }
        return bookBriefs;
    }

    public static List<OrderBrief> getOrderBriefs(List<Order> orders){
        List<OrderBrief> orderBriefs = new LinkedList<>();

        for(Order order:orders){
            OrderBrief orderBrief = new OrderBrief();
            orderBrief.setId(order.getId());
            orderBrief.setCustomerName(order.getBilling().getFirstName() + " " + order.getBilling().getLastName());
            orderBrief.setStatus(order.getStatus());
            orderBrief.setTotal(order.getTotal());
            orderBrief.setDate(order.getDatePurchased());

            orderBriefs.add(orderBrief);
        }

        return orderBriefs;
    }
}
