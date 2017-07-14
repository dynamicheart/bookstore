package com.dynamicheart.bookstore.store.store.model.catalog.publisher;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class ReadablePublisher extends PublisherEntity implements Serializable {

    private static final long serialVersionUID = 361318982620322096L;

    private PublisherDescription description;

    public PublisherDescription getDescription() {
        return description;
    }

    public void setDescription(PublisherDescription description) {
        this.description = description;
    }
}
