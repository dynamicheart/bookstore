package com.dynamicheart.bookstore.store.init.data;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.store.constants.Constants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dynamicheart on 5/28/2017.
 */

@Component
public class InitStoreData implements InitData{

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CustomerService customerService;

    @Inject
    private GroupService groupService;

    @Inject
    private BookService bookService;

    @Inject
    private OrderService orderService;

    @Override
    public void initInitialData() throws ServiceException {

        if(customerService.count() == 0){
            Customer customer = new Customer();
            customer.setNick("test");
            customer.setGender(CustomerGender.M);
            customer.setDateOfBirth(new Date());
            customer.setEmailAddress("test@bookstore.com");
            String password = passwordEncoder.encode("password");
            customer.setPassword(password);

            List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
            for(Group group : groups) {
                if(group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
                    customer.getGroups().add(group);
                }
            }

            customerService.create(customer);
        }

        if(bookService.count() == 0){
            Book book = new Book();
            book.setPrice(new BigDecimal(100));
            book.setQuantity(110);
            book.setAvailable(true);
            book.setDateAvailable(new Date());
            book.setBookReviewAvg(new BigDecimal(5));
            book.setBookReviewCount(100);
            book.setIsbn("7111212509");

            BookDescription bookDescription = new BookDescription();
            bookDescription.setBook(book);
            bookDescription.setName("Think in Java");
            bookDescription.setTitle("Think in Java");

            book.setDescription(bookDescription);

            bookService.create(book);
        }


        if(orderService.count() == 0){
            Order order = new Order();

            order.setCustomerId(new Long(102));
            order.setDatePurchased(new Date());
            order.setLastModified(new Date());
            order.setStatus(OrderStatus.ORDERED);

            OrderItem orderItem = new OrderItem();
            orderItem.setIsbn("7111212509");
            orderItem.setOrder(order);
            orderItem.setBookPrice(new BigDecimal(9));
            orderItem.setBookQuantity(1);
            orderItem.setBookName("Think in Java");

            Set<OrderItem> orderItems = new HashSet<>();
            orderItems.add(orderItem);

            order.setOrderItems(orderItems);
            orderService.create(order);
        }
    }
}
