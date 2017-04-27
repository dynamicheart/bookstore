package com.dynamicheart.bookstore.service;

import com.dynamicheart.bookstore.domain.Order;

import java.util.List;

/**
 * Created by dynamicheart on 4/23/2017.
 */
public interface OrderService {
    Order findOne(Long id);
    List<Order> findOrders();
    void update(Order order);
    void save(Order order);
    void delete(Long id);
    boolean isExist(Order order);
}
