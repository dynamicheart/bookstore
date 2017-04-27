package com.dynamicheart.bookstore.domain;

import com.dynamicheart.bookstore.utility.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by dynamicheart on 4/25/2017.
 */
public class TestDomain {
    @Test
    public void insertBook(){
        Book book = new Book();
        book.setISBN(new Long((long)123));
        book.setAuthor("Jianbang Yang");
        book.setPrice(15.9);
        book.setPublisher("JB");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

}
