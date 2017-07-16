package com.dynamicheart.bookstore.store.store.model.catalog.book;

import com.dynamicheart.bookstore.store.store.model.ReadableImage;
import com.dynamicheart.bookstore.store.store.model.catalog.publisher.ReadablePublisher;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class ReadableBook extends BookEntity implements Serializable {

    private static final long serialVersionUID = -7982702651833978097L;

    private ReadableAndPersistableBookDescription description;

    private ReadableImage defaultImage;

    private ReadablePublisher publisher;

    public ReadableAndPersistableBookDescription getDescription() {
        return description;
    }

    public void setDescription(ReadableAndPersistableBookDescription description) {
        this.description = description;
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
