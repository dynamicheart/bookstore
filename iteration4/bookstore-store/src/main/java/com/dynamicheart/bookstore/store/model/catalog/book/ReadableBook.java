package com.dynamicheart.bookstore.store.model.catalog.book;

import com.dynamicheart.bookstore.store.model.catalog.ReadableImage;
import com.dynamicheart.bookstore.store.model.catalog.publisher.ReadablePublisher;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class ReadableBook extends BookEntity implements Serializable {

    private static final long serialVersionUID = -7982702651833978097L;

    private AdaptableBookDescription description;

    private String displayPrice;

    private ReadableImage defaultImage;

    private ReadablePublisher publisher;

    public AdaptableBookDescription getDescription() {
        return description;
    }

    public void setDescription(AdaptableBookDescription description) {
        this.description = description;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public ReadableImage getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ReadableImage defaultImage) {
        this.defaultImage = defaultImage;
    }

    public ReadablePublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(ReadablePublisher publisher) {
        this.publisher = publisher;
    }
}
