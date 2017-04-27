package com.dynamicheart.bookstore.dao.hibernate5;

import com.dynamicheart.bookstore.dao.OrderDAO;
import com.dynamicheart.bookstore.domain.Order;
import com.dynamicheart.bookstore.utility.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dynamicheart on 4/23/2017.
 */
@Repository
public class OrderDAOImpl implements OrderDAO{

    @Override
    public List<Order> findOrders(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List orders = session.createQuery("from Order").list();
        session.getTransaction().commit();
        return orders;
    }

    @Override
    public List<Order> findOrders(Long max, int count){
        return null;
    }

    @Override
    public Order findOne(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Order order = (Order)session.load(Order.class, id);
        session.getTransaction().commit();
        return order;
    }

    @Override
    public void save(Order order){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    @Override
    public void update(Order order){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Order order = (Order)session.load(Order.class,id);
        session.delete(order);
        session.getTransaction().commit();
    }
}
