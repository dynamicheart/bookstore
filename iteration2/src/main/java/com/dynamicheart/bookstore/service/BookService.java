package com.dynamicheart.bookstore.service;

import com.dynamicheart.bookstore.domain.Book;

import java.util.List;

/**
 * Created by dynamicheart on 4/17/2017.
 */
public interface BookService {
    Book findOne(Long id);
    List<Book> findBooks();
    void update(Book book);
    void save(Book book);
    void delete(Long id);
    boolean isExist(Book book);
}
