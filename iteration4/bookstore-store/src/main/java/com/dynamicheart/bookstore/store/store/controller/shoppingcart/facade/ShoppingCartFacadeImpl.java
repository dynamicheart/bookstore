package com.dynamicheart.bookstore.store.store.controller.shoppingcart.facade;

import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by dynamicheart on 7/17/2017.
 */

@Component
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

    @Override
    public BigDecimal calculateShoppingCart(ShoppingCart cart) {
        BigDecimal grandTotal = new BigDecimal(0);
        grandTotal = grandTotal.setScale(2, RoundingMode.HALF_UP);

        for(ShoppingCartItem item:cart.getShoppingCartItems().values()){
            grandTotal = grandTotal.add(item.getSubTotal());
        }

        return grandTotal;
    }

    @Override
    public BigDecimal calculateSubTotal(ShoppingCartItem shoppingCartItem) {
        BigDecimal subTotal = new BigDecimal(0);
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);

        subTotal = shoppingCartItem.getBookPrice().multiply(new BigDecimal(shoppingCartItem.getQuantity()));
        return subTotal;
    }


}
