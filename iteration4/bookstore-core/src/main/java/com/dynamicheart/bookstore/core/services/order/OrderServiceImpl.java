package com.dynamicheart.bookstore.core.services.order;

import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.*;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
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

    @Override
    public BigDecimal calculateOrder(Order order) throws Exception {
        BigDecimal grandTotal = new BigDecimal(0);
        grandTotal = grandTotal.setScale(2, RoundingMode.HALF_UP);

        for(OrderItem item:order.getOrderItems()) {
            BigDecimal st = item.getOneTimeCharge().multiply(new BigDecimal(item.getItemQuantity()));
            grandTotal = grandTotal.add(st);
        }

        return grandTotal;
    }

    @Override
    public List<Order> listByCustomerId(Long customerId) {
        return orderRepository.listByCustomerId(customerId);
    }

    @Override
    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }
}
