package com.dynamicheart.bookstore.core.repositories.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
}
