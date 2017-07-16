package com.dynamicheart.bookstore.store.admin.model.statistics;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 7/16/2017.
 */
public class Criteria implements Serializable {

    private static final long serialVersionUID = 1035492884024727122L;

    private Long customerIds;

    private Date startDate;

    private Date endDate;

    private List bookIds;

    private List categoryIds;

    public Long getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(Long customerIds) {
        this.customerIds = customerIds;
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

    public List getBookIds() {
        return bookIds;
    }

    public void setBookIds(List bookIds) {
        this.bookIds = bookIds;
    }

    public List getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List categoryIds) {
        this.categoryIds = categoryIds;
    }
}
