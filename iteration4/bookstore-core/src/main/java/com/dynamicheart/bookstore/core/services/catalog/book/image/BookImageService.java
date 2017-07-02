package com.dynamicheart.bookstore.core.services.catalog.book.image;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImageSize;
import com.dynamicheart.bookstore.core.model.content.ImageContentFile;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;

/**
 * Created by dynamicheart on 6/6/2017.
 */
public interface BookImageService extends BookstoreEntityService<Long, BookImage> {

    /**
     * Add a BookImage to the persistence and an entry to the CMS
     */
    void addBookImage(Book book, BookImage bookImage, ImageContentFile inputImage)
            throws ServiceException;

    /**
     * Get the image ByteArrayOutputStream and content description from CMS
     */
    OutputContentFile getBookImage(BookImage bookImage, BookImageSize size)
            throws ServiceException;

    /**
     * Returns all Images for a given book
     */
    List<OutputContentFile> getBookImages(Book book)
            throws ServiceException;

    void removeBookImage(BookImage bookImage) throws ServiceException;

    void saveOrUpdate(BookImage bookImage) throws ServiceException;

    /**
     * Returns an image file from required identifier. This method is
     * used by the image servlet
     */
    OutputContentFile getBookImage(String bookCode, String fileName, final BookImageSize size) throws ServiceException;

    void addBookImages(Book book, List<BookImage> bookImages)
            throws ServiceException;

}
