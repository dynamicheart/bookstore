package com.dynamicheart.bookstore.core.model.order;


import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCartItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class OrderSummary implements Serializable {

	private OrderSummaryType orderSummaryType = OrderSummaryType.ORDERTOTAL;

	private static final long serialVersionUID = 1L;

	private List<ShoppingCartItem> books = new ArrayList<ShoppingCartItem>();

    public List<ShoppingCartItem> getBooks() {
        return books;
    }

    public void setBooks(List<ShoppingCartItem> books) {
        this.books = books;
    }

    public OrderSummaryType getOrderSummaryType() {
		return orderSummaryType;
	}

	public void setOrderSummaryType(OrderSummaryType orderSummaryType) {
		this.orderSummaryType = orderSummaryType;
	}

}
