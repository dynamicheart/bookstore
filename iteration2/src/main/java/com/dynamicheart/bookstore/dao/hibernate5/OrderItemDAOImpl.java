package com.dynamicheart.bookstore.dao.hibernate5;

import com.dynamicheart.bookstore.dao.OrderItemDAO;
import com.dynamicheart.bookstore.domain.OrderItem;
import com.dynamicheart.bookstore.utility.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by dynamicheart on 4/25/2017.
 */
public class OrderItemDAOImpl implements OrderItemDAO{

    @Override
    public List<OrderItem> findOrderItems(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List orderItems = session.createQuery("from OrderItem").list();
        session.getTransaction().commit();
        return orderItems;
    }

    @Override
    public List<OrderItem> findOrderItems(Long max, int count){
        return null;
    }

    @Override
    public OrderItem findOne(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        OrderItem orderItem = (OrderItem)session.load(OrderItem.class, id);
        session.getTransaction().commit();
        return orderItem;
    }

    @Override
    public void save(OrderItem orderItem){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(orderItem);
        session.getTransaction().commit();
    }

    @Override
    public void update(OrderItem orderItem){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(orderItem);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        OrderItem orderItem = (OrderItem)session.load(OrderItem.class,id);
        session.delete(orderItem);
        session.getTransaction().commit();
    }

}
