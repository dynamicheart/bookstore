package com.dynamicheart.bookstore.domain;

import com.dynamicheart.bookstore.domain.enumtype.OrderStatus;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dynamicheart on 4/12/2017.
 */

@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
    private Long id;
    private Long userId;
    private Timestamp placeTime;
    private OrderStatus status;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "order_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Column(name = "place_time", nullable = false)
    public Timestamp getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Timestamp placeTime) {
        this.placeTime = placeTime;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
