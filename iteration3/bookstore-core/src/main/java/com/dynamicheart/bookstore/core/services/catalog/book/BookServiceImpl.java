package com.dynamicheart.bookstore.core.services.catalog.book;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.repositories.catalog.book.BookRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;

/**
 * Created by dynamicheart on 5/23/2017.
 */

@Service("bookService")
public class BookServiceImpl extends BookstoreEntityServiceImpl<Long, Book> implements BookService{
    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    BookRepository bookRepository;

    @Inject
    public BookServiceImpl(BookRepository bookRepository) {
        super(bookRepository);
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveOrUpdateBookDescription(Book book, BookDescription description)
            throws ServiceException {

        if(book.getDescriptions()==null) {
            book.setDescriptions(new HashSet<BookDescription>());
        }

        book.getDescriptions().add(description);
        description.setBook(book);
        update(book);
    }

    @Override
    public BookDescription getBookDescription(Book book) {
        return null;
    }

    @Override
    public Book getByCode(String bookCode) {
        return bookRepository.getByCode(bookCode);
    }
}
