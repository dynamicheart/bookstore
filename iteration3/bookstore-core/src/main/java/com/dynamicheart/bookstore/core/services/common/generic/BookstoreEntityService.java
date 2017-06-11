package com.dynamicheart.bookstore.core.services.common.generic;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


public interface BookstoreEntityService<K extends Serializable & Comparable<K>, E extends BookstoreEntity<K, ?>> extends TransactionalAspectAwareService{

	void save(E entity) throws ServiceException;

	void update(E entity) throws ServiceException;

	void create(E entity) throws ServiceException;

	void delete(E entity) throws ServiceException;

	E getById(K id);

	List<E> list();

	Long count();

	public Page<E> findPaginated(int page, int size);
}
