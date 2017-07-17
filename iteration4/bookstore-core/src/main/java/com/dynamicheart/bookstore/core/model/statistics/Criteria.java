package com.dynamicheart.bookstore.core.model.statistics;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 7/16/2017.
 */
public class Criteria implements Serializable {

    private static final long serialVersionUID = 1035492884024727122L;

    private Long customerId;

    private Date startDate;

    private Date endDate;

    private String bookIsbn;

    private Long categoryId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
