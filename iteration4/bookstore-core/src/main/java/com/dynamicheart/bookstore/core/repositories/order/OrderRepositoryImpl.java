package com.dynamicheart.bookstore.core.repositories.order;

import com.dynamicheart.bookstore.core.model.statistics.Criteria;
import com.dynamicheart.bookstore.core.model.statistics.Stastistics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Stastistics getStastisticsByCriteria(Criteria criteria) {
        try {

            StringBuilder qs = new StringBuilder();
            qs.append("select sum(o.total) from Order as o ");
            qs.append("where b.id=:bid");

            String hql = qs.toString();
            Query q = this.em.createQuery(hql);


        } catch(javax.persistence.NoResultException ers) {
            return null;
        }
        return null;
    }
}
