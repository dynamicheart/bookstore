package com.dynamicheart.bookstore.dao;


import com.dynamicheart.bookstore.domain.OrderItem;

import java.util.List;

/**
 * Created by dynamicheart on 4/25/2017.
 */
public interface OrderItemDAO {
    public List<OrderItem> findOrderItems();

    public List<OrderItem> findOrderItems(Long max, int count);

    public OrderItem findOne(Long id);

    public void save(OrderItem user);

    public void update(OrderItem user);

    public void delete(Long id);
}
