package com.dynamicheart.bookstore.core.model.statistics;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dynamicheart on 7/16/2017.
 */
public class Statistics implements Serializable{

    private static final long serialVersionUID = -3578008116742661476L;

    private int quantity;

    private BigDecimal total;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
