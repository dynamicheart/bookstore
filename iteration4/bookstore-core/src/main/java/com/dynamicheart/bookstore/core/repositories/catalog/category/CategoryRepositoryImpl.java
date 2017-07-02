package com.dynamicheart.bookstore.core.repositories.catalog.category;

import com.dynamicheart.bookstore.core.model.catalog.catagory.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
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
		qs.append("and book.available=true and book.dateAvailable<=:dt ");
		qs.append("group by categories.id");
		
    	String hql = qs.toString();
		Query q = this.em.createQuery(hql);

    	q.setParameter("cid", categoryIds);
    	q.setParameter("dt", new Date());

    	@SuppressWarnings("unchecked")
		List<Object[]> counts =  q.getResultList();

    	
    	return counts;
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> listByParent(Category category) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select c from Category c");
		

		if (category == null) {
			queryBuilder.append(" where c.parent IsNull ");
		} else {
			queryBuilder.append(" join fetch c.parent cp where cp.id =:cid ");
		}

		
		queryBuilder.append(" order by c.sortOrder asc");
		
    	String hql = queryBuilder.toString();
		Query q = this.em.createQuery(hql);

    	q.setParameter("cid", category.getId());
		
		return q.getResultList();
	}

}
