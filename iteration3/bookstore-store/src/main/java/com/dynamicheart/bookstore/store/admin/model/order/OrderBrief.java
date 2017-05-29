package com.dynamicheart.bookstore.store.admin.model.order;

import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dynamicheart on 5/29/2017.
 */
public class OrderBrief implements Serializable{

    private static final long serialVersionUID = -5640826303632772009L;

    private Long id;

    private Long customerId;

    private BigDecimal total;

    private Date datePurchased;

    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
