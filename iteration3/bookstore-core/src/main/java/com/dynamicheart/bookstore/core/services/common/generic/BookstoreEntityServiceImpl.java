package com.dynamicheart.bookstore.core.services.common.generic;


import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <T> entity type
 */
public abstract class BookstoreEntityServiceImpl<K extends Serializable & Comparable<K>, E extends BookstoreEntity<K, ?>>
	implements BookstoreEntityService<K, E> {
	
	/**
	 * Classe de l'entité, déterminé à partir des paramètres generics.
	 */
	private Class<E> objectClass;


    private JpaRepository<E, K> repository;
    private DataTablesRepository<E, K> dataTablesRepository;

	@SuppressWarnings("unchecked")
	public BookstoreEntityServiceImpl(JpaRepository<E, K> repository) {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
		this.repository = repository;
	}

    @SuppressWarnings("unchecked")
    public BookstoreEntityServiceImpl(JpaRepository<E, K> repository, DataTablesRepository<E, K> dataTablesRepository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
        this.dataTablesRepository = dataTablesRepository;
    }
	
	protected final Class<E> getObjectClass() {
		return objectClass;
	}


	public E getById(K id) {
		return repository.findOne(id);
	}

	
	public void save(E entity) throws ServiceException {
		repository.saveAndFlush(entity);
	}
	
	
	public void create(E entity) throws ServiceException {
		save(entity);
	}

	
	
	public void update(E entity) throws ServiceException {
		save(entity);
	}
	

	public void delete(E entity) throws ServiceException {
		repository.delete(entity);
	}
	
	
	public void flush() {
		repository.flush();
	}
	

	
	public List<E> list() {
		return repository.findAll();
	}
	

	public Long count() {
		return repository.count();
	}

    @Override
    public DataTablesOutput<E> findAll(DataTablesInput input) {
        return dataTablesRepository.findAll(input);
    }
}