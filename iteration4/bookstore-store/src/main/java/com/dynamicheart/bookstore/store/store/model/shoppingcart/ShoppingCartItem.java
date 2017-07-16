package com.dynamicheart.bookstore.store.store.model.shoppingcart;

import com.dynamicheart.bookstore.store.store.model.ReadableImage;
import com.dynamicheart.bookstore.store.store.model.StoreEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dynamicheart on 7/15/2017.
 */
public class ShoppingCartItem extends StoreEntity implements Serializable {

    private static final long serialVersionUID = 6451649388080331478L;
    private String name;
    private ReadableImage image;
    private BigDecimal bookPrice;
    private int quantity = 0;
    private long bookId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReadableImage getImage() {
        return image;
    }

    public void setImage(ReadableImage image) {
        this.image = image;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

}
