package com.dynamicheart.bookstore.core.model.customer.avatar;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;

import javax.persistence.*;

/**
 * Created by dynamicheart on 7/5/2017.
 */

@Entity
@Table(name = "CUSTOMER_AVATAR", schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class CustomerAvatar extends BookstoreEntity<Long, CustomerAvatar> {

    private static final long serialVersionUID = 1741994828953261377L;

    @Id
    @Column(name = "CUSTOMER_AVATAR_ID")
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUSTOMER_AVATAR_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "DEFAULT_AVATAR")
    private boolean defaultAvatar = true;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDefaultAvatar() {
        return defaultAvatar;
    }

    public void setDefaultAvatar(boolean defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
