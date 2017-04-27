package com.dynamicheart.bookstore.dao;

import com.dynamicheart.bookstore.domain.Order;

import java.util.List;

/**
 * Created by dynamicheart on 4/23/2017.
 */
public interface OrderDAO {
    public List<Order> findOrders();

    public List<Order> findOrders(Long max, int count);

    public Order findOne(Long id);

    public void save(Order order);

    public void update(Order order);

    public void delete(Long id);
}
