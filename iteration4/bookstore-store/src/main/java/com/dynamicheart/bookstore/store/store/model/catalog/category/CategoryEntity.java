package com.dynamicheart.bookstore.store.store.model.catalog.category;

import java.io.Serializable;

public class CategoryEntity extends Category implements Serializable {


    private static final long serialVersionUID = -6439245868434040904L;

    private int sortOrder;

    private boolean visible;

    private String lineage;

    private int depth;

    private Category parent;

    public int getSortOrder() {
        return sortOrder;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getLineage() {
        return lineage;
    }

    public int getDepth() {
        return depth;
    }

    public Category getParent() {
        return parent;
    }
}
