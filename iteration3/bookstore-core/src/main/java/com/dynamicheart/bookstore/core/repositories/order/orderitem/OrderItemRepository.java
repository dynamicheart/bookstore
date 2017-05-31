package com.dynamicheart.bookstore.core.repositories.order.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi from OrderItem oi join fetch oi.order o where o.id = ?1")
    List<OrderItem> findByOrderId(Long id);
}
