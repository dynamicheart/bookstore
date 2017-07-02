package com.dynamicheart.bookstore.core.modules.cms.book;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;

/**
 * Created by dynamicheart on 7/2/2017.
 */
public interface BookImageRemove {

    public void removeBookImage(BookImage bookImage) throws ServiceException;
    public void removeBookImages(Book book) throws ServiceException;
}
