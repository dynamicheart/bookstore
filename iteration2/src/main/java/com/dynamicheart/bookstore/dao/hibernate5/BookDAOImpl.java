package com.dynamicheart.bookstore.dao.hibernate5;

import com.dynamicheart.bookstore.dao.BookDAO;
import com.dynamicheart.bookstore.domain.Book;
import com.dynamicheart.bookstore.utility.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dynamicheart on 4/17/2017.
 */
@Repository
public class BookDAOImpl implements BookDAO {

    @Override
    public List<Book> findBooks(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List books = session.createQuery("from Book").list();
        session.getTransaction().commit();
        return books;
    }

    @Override
    public List<Book> findBooks(Long max, int count){
        return null;
    }

    @Override
    public Book findOne(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Book book = (Book)session.load(Book.class, id);
        session.getTransaction().commit();
        return book;
    }

    @Override
    public void save(Book book){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

    @Override
    public void update(Book book){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Book book = (Book)session.load(Book.class,id);
        session.delete(book);
        session.getTransaction().commit();
    }
}
