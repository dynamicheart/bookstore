package com.dynamicheart.bookstore.service.impl;

import com.dynamicheart.bookstore.dao.BookDAO;
import com.dynamicheart.bookstore.domain.Book;
import com.dynamicheart.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dynamicheart on 4/17/2017.
 */
@Service
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    @Autowired
    public void setBookDAO(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @Override
    public Book findOne(Long id) {
        return bookDAO.findOne(id);
    }

    @Override
    public List<Book> findBooks() {
        return bookDAO.findBooks();
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void delete(Long id) {
        bookDAO.delete(id);
    }

    @Override
    public boolean isExist(Book book) {
        return false;
    }
}
