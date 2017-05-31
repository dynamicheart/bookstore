package com.dynamicheart.bookstore.core.repositories.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book getById(Long bookId) {
        try {

            StringBuilder qs = new StringBuilder();
            qs.append("select distinct b from Book as b ");
            qs.append("join fetch b.availabilities ba ");
            qs.append("join fetch b.descriptions bd ");
            qs.append("left join fetch ba.prices bap ");
            qs.append("left join fetch bap.descriptions bapd ");

            //other lefts
            qs.append("left join fetch b.publisher publ ");
            qs.append("left join fetch publ.descriptions publd ");

            qs.append("where b.id=:bid");

            String hql = qs.toString();
            Query q = this.em.createQuery(hql);

            q.setParameter("bid", bookId);

            Book p = (Book)q.getSingleResult();


            return p;

        } catch(javax.persistence.NoResultException ers) {
            return null;
        }
    }

    @Override
    public Book getByCode(String bookCode) {
        try {

            StringBuilder qs = new StringBuilder();
            qs.append("select distinct b from Book as b ");
            qs.append("join fetch b.availabilities ba ");
            qs.append("join fetch b.descriptions bd ");
            qs.append("left join fetch ba.prices bap ");
            qs.append("left join fetch bap.descriptions bapd ");

            //other lefts
            qs.append("left join fetch b.publisher publ ");
            qs.append("left join fetch publ.descriptions publd ");

            qs.append("where b.isbn=:code ");

            String hql = qs.toString();
            Query q = this.em.createQuery(hql);

            q.setParameter("code", bookCode);

            Book p = (Book)q.getSingleResult();


            return p;

        } catch(javax.persistence.NoResultException ers) {
            return null;
        }
    }
}
