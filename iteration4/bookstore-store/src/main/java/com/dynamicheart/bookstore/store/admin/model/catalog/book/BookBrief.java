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

    private Boolean available;

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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
