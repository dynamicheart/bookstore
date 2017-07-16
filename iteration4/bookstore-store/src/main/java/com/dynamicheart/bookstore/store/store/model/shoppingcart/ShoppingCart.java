package com.dynamicheart.bookstore.store.store.model.shoppingcart;

import com.dynamicheart.bookstore.store.store.model.StoreEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/15/2017.
 */
@Component
@Scope("prototype")
public class ShoppingCart extends StoreEntity implements Serializable{

    private static final long serialVersionUID = 5345470614399136558L;

    private String total;

    private Map<String,ShoppingCartItem> shoppingCartItems = new HashMap<>();

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Map<String, ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(Map<String, ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }
}
