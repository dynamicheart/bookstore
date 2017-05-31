package com.dynamicheart.bookstore.core.model.order;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.Billing;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by dynamicheart on 5/23/2017.
 */

@Entity
@Table(name = "ORDERS", schema = SchemaConstant.BOOKSTORE_SCHEMA)
public class Order extends BookstoreEntity<Long, Order> {

    private static final long serialVersionUID = -3266122579306661932L;

    @Id
    @Column(name ="ORDER_ID" , unique=true , nullable=false )
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ORDER_ID_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column (name ="ORDER_STATUS")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="LAST_MODIFIED")
    private Date lastModified;

    //the customer object can be detached. An order can exist and the customer deleted
    @Column (name ="CUSTOMER_ID")
    private Long customerId;

    @Temporal(TemporalType.DATE)
    @Column (name ="DATE_PURCHASED")
    private Date datePurchased;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="ORDER_DATE_FINISHED")
    private Date orderDateFinished;

    @Column (name ="ORDER_TOTAL")
    private BigDecimal total;

    @Valid
    @Embedded
    private Billing billing = null;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new LinkedHashSet<OrderItem>();

    @Column (name ="CUSTOMER_EMAIL_ADDRESS", length=50, nullable=false)
    private String customerEmailAddress;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Date getOrderDateFinished() {
        return orderDateFinished;
    }

    public void setOrderDateFinished(Date orderDateFinished) {
        this.orderDateFinished = orderDateFinished;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }
}
