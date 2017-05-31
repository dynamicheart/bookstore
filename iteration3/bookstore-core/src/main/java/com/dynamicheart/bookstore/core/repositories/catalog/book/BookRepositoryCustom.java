package com.dynamicheart.bookstore.core.repositories.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface BookRepositoryCustom {

    Book getById(Long bookId);

    Book getByCode(String bookCode);
}
