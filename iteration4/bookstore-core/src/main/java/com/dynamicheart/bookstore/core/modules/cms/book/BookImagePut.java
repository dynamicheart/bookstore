package com.dynamicheart.bookstore.core.modules.cms.book;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.content.ImageContentFile;

/**
 * Created by dynamicheart on 7/2/2017.
 */
public interface BookImagePut {

    public void addBookImage(BookImage bookImage, ImageContentFile contentImage) throws ServiceException;

}
