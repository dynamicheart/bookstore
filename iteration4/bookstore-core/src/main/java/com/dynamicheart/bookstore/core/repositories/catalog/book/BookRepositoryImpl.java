package com.dynamicheart.bookstore.core.repositories.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

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

    @SuppressWarnings("rawtypes")
    @Override
    public List<Book> getBooksListByCategories(Set categoryIds) {
        StringBuilder qs = new StringBuilder();
        qs.append("select distinct b from Book as b ");
        qs.append("join fetch b.availabilities ba ");
        qs.append("left join fetch ba.prices bap ");

        qs.append("join fetch b.descriptions bd ");
        qs.append("join fetch b.categories categs ");

        qs.append("left join fetch bap.descriptions bapd ");

        qs.append("left join fetch b.publisher publ ");

        qs.append("where categs.id in (:cid)");

        String hql = qs.toString();
        Query q = this.em.createQuery(hql);

        q.setParameter("cid", categoryIds);


        @SuppressWarnings("unchecked")
        List<Book> books =  q.getResultList();

        return books;

    }

    @Override
    public List<Book> getBooksListByCategories(Set<Long> categoryIds, Language language) {
        StringBuilder qs = new StringBuilder();
        qs.append("select distinct b from Book as b ");
        qs.append("join fetch b.availabilities ba ");
        qs.append("left join fetch ba.prices bap ");

        qs.append("join fetch b.descriptions bd ");
        qs.append("join fetch b.categories categs ");

        qs.append("left join fetch bap.descriptions bapd ");

        qs.append("left join fetch b.publisher publ ");

        qs.append("where categs.id in (:cid)");

        qs.append("and bd.language.id=:lang and papd.language.id=:lang ");
        qs.append("and b.available=true");

        String hql = qs.toString();
        Query q = this.em.createQuery(hql);

        q.setParameter("cid", categoryIds);
        q.setParameter("lang", language.getId());



        @SuppressWarnings("unchecked")
        List<Book> books =  q.getResultList();

        return books;
    }

    @Override
    public Book getByFriendlyUrl(String seUrl, Locale locale) {

        List regionList = new ArrayList();
        regionList.add("*");
        regionList.add(locale.getCountry());


        StringBuilder qs = new StringBuilder();
        qs.append("select distinct b from Book as b ");
        qs.append("join fetch b.availabilities ba ");
        qs.append("join fetch b.descriptions bd ");


        //images
        qs.append("left join fetch b.images images ");

        //other lefts
        qs.append("left join fetch b.publisher publi ");
        qs.append("left join fetch publi.descriptions publid ");

        qs.append("where ba.region in (:lid) ");
        qs.append("and bd.seUrl=:seUrl ");
        qs.append("and b.available=true");



        String hql = qs.toString();
        Query q = this.em.createQuery(hql);


        q.setParameter("lid", regionList);
        q.setParameter("seUrl", seUrl);

        Book b = null;

        try {
            b = (Book)q.getSingleResult();
        } catch(javax.persistence.NoResultException ignore) {

        }

        return b;
    }
}
