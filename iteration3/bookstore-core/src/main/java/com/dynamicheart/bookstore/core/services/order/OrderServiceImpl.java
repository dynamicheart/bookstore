package com.dynamicheart.bookstore.core.services.order;

import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.repositories.order.OrderRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public class OrderServiceImpl extends BookstoreEntityServiceImpl<Long, Order> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Inject
    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
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
