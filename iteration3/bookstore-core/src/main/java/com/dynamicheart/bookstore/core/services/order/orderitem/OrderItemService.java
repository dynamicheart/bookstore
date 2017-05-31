package com.dynamicheart.bookstore.core.services.order.orderitem;

import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderItemService extends BookstoreEntityService<Long, OrderItem> {
    /**
     * List {@link OrderItem} by order id
     * @param orderId
     * @return
     */
    List<OrderItem> getByOrderId(Long orderId);
}
