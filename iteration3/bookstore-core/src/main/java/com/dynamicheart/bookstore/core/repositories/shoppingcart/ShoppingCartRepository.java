package com.dynamicheart.bookstore.core.repositories.shoppingcart;

import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	@Query("select c from ShoppingCart c left join fetch c.lineItems cl where c.id = ?1")
	ShoppingCart findOne(Long id);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl where c.shoppingCartCode = ?1")
	ShoppingCart findByCode(String code);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl where c.customerId = ?1")
	ShoppingCart findByCustomer(Long customerId);
	
}
