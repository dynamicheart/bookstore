package com.dynamicheart.bookstore.store.model.catalog;

import com.dynamicheart.bookstore.store.model.Entity;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class ReadableImage extends Entity implements Serializable {


    private static final long serialVersionUID = 921135462094254176L;

    private String resourceId;

    private String imageUrl;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
