package com.dynamicheart.bookstore.store.store.model.catalog.publisher;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class PublisherEntity extends Publisher implements Serializable {

    private static final long serialVersionUID = -3194784899411215016L;

    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
