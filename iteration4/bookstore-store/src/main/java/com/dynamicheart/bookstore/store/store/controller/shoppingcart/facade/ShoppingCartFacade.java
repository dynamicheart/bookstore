package com.dynamicheart.bookstore.store.store.controller.shoppingcart.facade;

import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCartItem;

import java.math.BigDecimal;

/**
 * Created by dynamicheart on 7/17/2017.
 */
public interface ShoppingCartFacade {

    BigDecimal calculateShoppingCart(ShoppingCart cart);

    BigDecimal calculateSubTotal(ShoppingCartItem shoppingCartItem);

}
