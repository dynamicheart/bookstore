package com.dynamicheart.bookstore.service.impl;

import com.dynamicheart.bookstore.dao.OrderDAO;
import com.dynamicheart.bookstore.domain.Order;
import com.dynamicheart.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dynamicheart on 4/23/2017.
 */
@Service
public class OrderServiceImpl implements OrderService{
    private OrderDAO orderDAO;

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @Override
    public Order findOne(Long id) {
        return orderDAO.findOne(id);
    }

    @Override
    public List<Order> findOrders() {
        return orderDAO.findOrders();
    }

    @Override
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Override
    public void save(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void delete(Long id) {
        orderDAO.delete(id);
    }

    @Override
    public boolean isExist(Order order) {
        return false;
    }
}
