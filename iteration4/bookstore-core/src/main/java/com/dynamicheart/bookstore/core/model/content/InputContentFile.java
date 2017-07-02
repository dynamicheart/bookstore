package com.dynamicheart.bookstore.core.model.content;

import java.io.InputStream;
import java.io.Serializable;


public class InputContentFile extends StaticContentFile implements Serializable 
{

    private static final long serialVersionUID = -6755432946390903007L;

    private InputStream file;
    
    public InputStream getFile()
    {
        return file;
    }
    public void setFile( InputStream file )
    {
        this.file = file;
    }

}