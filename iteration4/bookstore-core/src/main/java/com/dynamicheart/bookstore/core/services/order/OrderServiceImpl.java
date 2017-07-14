package com.dynamicheart.bookstore.core.services.order;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.*;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCartItem;
import com.dynamicheart.bookstore.core.repositories.order.OrderRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by dynamicheart on 5/23/2017.
 */

@Service("orderService")
public class OrderServiceImpl extends BookstoreEntityServiceImpl<Long, Order> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Inject
    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    private BigDecimal caculateOrder(OrderSummary summary, Customer customer, final Language language) throws Exception {

        BigDecimal grandTotal = new BigDecimal(0);
        grandTotal.setScale(2, RoundingMode.HALF_UP);

        for(ShoppingCartItem item : summary.getBooks()) {
            BigDecimal st = item.getItemPrice().multiply(new BigDecimal(item.getQuantity()));
            item.setSubTotal(st);
            grandTotal.add(st);
        }

        return grandTotal;
    }


    @Override
    public BigDecimal caculateOrderTotal(final OrderSummary orderSummary, final Customer customer, final Language language) throws ServiceException {
        Validate.notNull(orderSummary,"Order summary cannot be null");
        Validate.notNull(orderSummary.getBooks(),"Order summary.books cannot be null");
        Validate.notNull(customer,"Customer cannot be null");

        try {
            return caculateOrder(orderSummary, customer,language);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }



    @Override
    public BigDecimal caculateOrderTotal(final OrderSummary orderSummary, final Language language) throws ServiceException {
        Validate.notNull(orderSummary,"Order summary cannot be null");
        Validate.notNull(orderSummary.getBooks(),"Order summary.books cannot be null");

        try {
            return caculateOrder(orderSummary, null, language);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    private BigDecimal caculateShoppingCart( final ShoppingCart shoppingCart, final Customer customer, final Language language) throws Exception {
        OrderSummary orderSummary = new OrderSummary();
        orderSummary.setOrderSummaryType(OrderSummaryType.SHOPPINGCART);

        List<ShoppingCartItem> itemsSet = new ArrayList<ShoppingCartItem>(shoppingCart.getLineItems());
        orderSummary.setBooks(itemsSet);

        return this.caculateOrder(orderSummary, customer, language);
    }


    @Override
    public BigDecimal calculateShoppingCartTotal(
            final ShoppingCart shoppingCart, final Customer customer,
            final Language language) throws ServiceException {
        Validate.notNull(shoppingCart,"Order summary cannot be null");
        Validate.notNull(customer,"Customery cannot be null");
        try {
            return  caculateShoppingCart(shoppingCart, customer, language);
        } catch (Exception e) {
            LOGGER.error( "Error while calculating shopping cart total" +e );
            throw new ServiceException(e);
        }

    }

    @Override
    public BigDecimal calculateShoppingCartTotal(
            final ShoppingCart shoppingCart, final Language language)
            throws ServiceException {
        Validate.notNull(shoppingCart,"Order summary cannot be null");

        try {
            return caculateShoppingCart(shoppingCart, null, language);
        } catch (Exception e) {
            LOGGER.error( "Error while calculating shopping cart total" +e );
            throw new ServiceException(e);
        }
    }


    @Override
    public void saveOrUpdate(final Order order) throws ServiceException {

        if(order.getId()!=null && order.getId()>0) {
            LOGGER.debug("Updating Order");
            super.update(order);

        } else {
            LOGGER.debug("Creating Order");
            super.create(order);
        }
    }
}
