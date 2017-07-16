package com.dynamicheart.bookstore.core.repositories.catalog.category;

import com.dynamicheart.bookstore.core.model.catalog.category.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<Object[]> countBooksByCategories(List<Long> categoryIds) {


		StringBuilder qs = new StringBuilder();
		qs.append("select categories, count(book.id) from Book book ");
		qs.append("inner join book.categories categories ");
		qs.append("where categories.id in (:cid) ");
		qs.append("and book.available=true");
		qs.append("group by categories.id");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("cid", categoryIds);

		@SuppressWarnings("unchecked")
		List<Object[]> counts = q.getResultList();


		return counts;
	}

}
