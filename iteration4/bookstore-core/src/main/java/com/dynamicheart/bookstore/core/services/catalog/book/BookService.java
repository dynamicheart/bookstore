package com.dynamicheart.bookstore.core.services.catalog.book;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;
import java.util.Locale;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface BookService extends BookstoreEntityService<Long, Book> {
    void saveOrUpdateBookDescription(Book book, BookDescription description) throws ServiceException;

    BookDescription getBookDescription(Book book);

    Book getByCode(String bookCode);

    List<Book> getBooks(List<Long> categoryIds) throws ServiceException;

    Book getBySeUrl(String seUrl, Locale locale);

    List<Book> getBooksByCriteria(String criteria);
}
