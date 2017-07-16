package com.dynamicheart.bookstore.core.services.order;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderService extends BookstoreEntityService<Long, Order>{
    void saveOrUpdate(Order order) throws ServiceException;

    BigDecimal calculateOrder(Order order) throws Exception;

    List<Order> listByCustomerId(Long customerId);

    Order findOne(Long id);
}
