package com.dynamicheart.bookstore.core.services.order;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.OrderSummary;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.math.BigDecimal;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderService extends BookstoreEntityService<Long, Order>{
    void saveOrUpdate(Order order) throws ServiceException;

    BigDecimal caculateOrderTotal(OrderSummary orderSummary, Customer customer, Language language) throws ServiceException;

    BigDecimal caculateOrderTotal(OrderSummary orderSummary, Language language) throws ServiceException;

    BigDecimal calculateShoppingCartTotal(final ShoppingCart shoppingCart, final Customer customer, final Language language) throws ServiceException;

    BigDecimal calculateShoppingCartTotal(final ShoppingCart shoppingCart, final Language language) throws ServiceException;
}
