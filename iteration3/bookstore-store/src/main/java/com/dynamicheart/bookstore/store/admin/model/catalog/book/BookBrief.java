package com.dynamicheart.bookstore.store.admin.model.catalog.book;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dynamicheart on 5/29/2017.
 */
public class BookBrief implements Serializable {

    private static final long serialVersionUID = -7328787667113419588L;

    private Long id;

    private String isbn;

    private String title;

    private BigDecimal price;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
