package com.dynamicheart.bookstore.dao;

import com.dynamicheart.bookstore.domain.Book;

import java.util.List;

/**
 * Created by dynamicheart on 4/17/2017.
 */
public interface BookDAO {
    public List<Book> findBooks();

    public List<Book> findBooks(Long max, int count);

    public Book findOne(Long id);

    public void save(Book book);

    public void update(Book book);

    public void delete(Long id);
}
