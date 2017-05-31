package com.dynamicheart.bookstore.core.services.order.orderitem;

import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.repositories.order.orderitem.OrderItemRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */

@Service("orderItemService")
public class OrderItemServiceImpl extends BookstoreEntityServiceImpl<Long, OrderItem> implements OrderItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    private final OrderItemRepository orderItemRepository;

    @Inject
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        super(orderItemRepository);
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> getByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
