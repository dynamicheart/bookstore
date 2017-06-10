package com.dynamicheart.bookstore.core.repositories.shoppingcart;

import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
}
