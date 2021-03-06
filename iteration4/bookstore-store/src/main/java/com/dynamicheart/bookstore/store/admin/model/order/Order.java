package com.dynamicheart.bookstore.store.admin.model.order;

import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dynamicheart on 6/1/2017.
 */
public class Order implements Serializable{

    private static final long serialVersionUID = 7306023537663430139L;

    private Long Id;

    List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());

    private String datePurchased = "";

    private  com.dynamicheart.bookstore.core.model.order.Order order;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<OrderStatus> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<OrderStatus> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public com.dynamicheart.bookstore.core.model.order.Order getOrder() {
        return order;
    }

    public void setOrder(com.dynamicheart.bookstore.core.model.order.Order order) {
        this.order = order;
    }

}
