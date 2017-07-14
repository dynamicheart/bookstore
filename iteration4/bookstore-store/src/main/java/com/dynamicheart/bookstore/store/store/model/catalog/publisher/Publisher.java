package com.dynamicheart.bookstore.store.store.model.catalog.publisher;

import com.dynamicheart.bookstore.store.store.model.Entity;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class Publisher extends Entity implements Serializable {

    private static final long serialVersionUID = -7395346931675185243L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
