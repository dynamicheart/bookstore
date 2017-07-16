package com.dynamicheart.bookstore.core.repositories.order;

import com.dynamicheart.bookstore.core.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    @Query("select o from Order o join fetch o.orderItems oi where o.id = ?1")
    Order findOne(Long id);

    @Query("select o from Order o where o.customerId = ?1")
    List<Order> listByCustomerId(Long customerId);
}
