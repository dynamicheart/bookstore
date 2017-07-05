package com.dynamicheart.bookstore.core.model.content;

import com.dynamicheart.bookstore.core.model.common.ContentFile;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by dynamicheart on 7/4/2017.
 */

public class OutputContentFile extends ContentFile implements Serializable{

    private static final long serialVersionUID = -4098838257639229402L;

    private ByteArrayOutputStream file;

    public ByteArrayOutputStream getFile() {
        return file;
    }

    public void setFile(ByteArrayOutputStream file) {
        this.file = file;
    }
}
