package com.dynamicheart.bookstore.core.model.content;


import com.dynamicheart.bookstore.core.model.common.ContentFile;
import com.mongodb.BasicDBObject;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by dynamicheart on 6/2/2017.
 */

public class InputContentFile extends ContentFile implements Serializable{

    private static final long serialVersionUID = 8137447882127445908L;

    private InputStream file;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

}
