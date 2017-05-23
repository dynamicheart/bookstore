package com.dynamicheart.bookstore.core.services.order;

import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderService extends BookstoreEntityService<Long, Order>{
    void saveOrUpdate(Order order) throws ServiceException;
}
