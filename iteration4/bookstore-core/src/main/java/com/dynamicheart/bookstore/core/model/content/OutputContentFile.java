package com.dynamicheart.bookstore.core.model.content;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Data class responsible for carrying out static content data from MongoDB to
 * service layer.
 *
 */
public class OutputContentFile extends StaticContentFile implements Serializable
{

    private static final long serialVersionUID = 4620710170894908198L;

    private ByteArrayOutputStream file;
    public ByteArrayOutputStream getFile()
    {
        return file;
    }
    public void setFile( ByteArrayOutputStream file )
    {
        this.file = file;
    }
    
}