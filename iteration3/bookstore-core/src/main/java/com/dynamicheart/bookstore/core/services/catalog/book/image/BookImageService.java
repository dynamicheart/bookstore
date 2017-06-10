package com.dynamicheart.bookstore.core.services.catalog.book.image;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;
import org.bson.types.ObjectId;

/**
 * Created by dynamicheart on 6/6/2017.
 */
public interface BookImageService extends BookstoreEntityService<ObjectId, BookImage> {
    void addBookImage(Book book, BookImage bookImage)
            throws ServiceException;
    
    
}
