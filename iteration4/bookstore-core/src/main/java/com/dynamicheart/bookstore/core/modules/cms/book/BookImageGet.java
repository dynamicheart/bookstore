package com.dynamicheart.bookstore.core.modules.cms.book;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImageSize;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;

import java.util.List;

/**
 * Created by dynamicheart on 7/2/2017.
 */

public interface BookImageGet {

    public OutputContentFile getBookImage(final String bookIsbn, final String imageName) throws ServiceException;
    public OutputContentFile getBookImage(final String bookIsbn, final String imageName, final BookImageSize size) throws ServiceException;
    public OutputContentFile getBookImage(BookImage bookImage) throws ServiceException;
    public List<OutputContentFile> getImages(Book book) throws ServiceException;
}
