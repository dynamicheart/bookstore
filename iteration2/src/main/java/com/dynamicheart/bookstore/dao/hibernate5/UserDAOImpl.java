package com.dynamicheart.bookstore.dao.hibernate5;

import com.dynamicheart.bookstore.dao.UserDAO;
import com.dynamicheart.bookstore.domain.User;
import com.dynamicheart.bookstore.utility.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dynamicheart on 4/19/2017.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findUsers(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List users = session.createQuery("from User").list();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public List<User> findUsers(Long max, int count){
        return null;
    }

    @Override
    public User findOne(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User)session.load(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public void save(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User)session.load(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
    }

}
