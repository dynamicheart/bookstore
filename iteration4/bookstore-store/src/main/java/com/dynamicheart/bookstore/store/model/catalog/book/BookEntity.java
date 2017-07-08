package com.dynamicheart.bookstore.store.model.catalog.book;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class BookEntity extends Book implements Serializable{

    private static final long serialVersionUID = -742534354081338936L;

    private BigDecimal price;

    private int quantity = 0;

    private String isbn;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
