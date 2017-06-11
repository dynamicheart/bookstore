package com.dynamicheart.bookstore.core.services.common.generic;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by dynamicheart on 6/7/2017.
 */
public abstract class BookstoreEntityServiceMongoImpl<K extends Serializable & Comparable<K>, E extends BookstoreEntity<K, ?>>
        implements BookstoreEntityService<K, E>  {

    private Class<E> objectClass;

    private MongoRepository<E, K> repository;

    @SuppressWarnings("unchecked")
    public BookstoreEntityServiceMongoImpl(MongoRepository<E, K> repository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }


    public E getById(K id) {
        return repository.findOne(id);
    }

    public void save(E entity) throws ServiceException {
        repository.save(entity);
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

    public List<E> list() {
        return repository.findAll();
    }


    public Long count() {
        return repository.count();
    }

    public Page<E> findPaginated(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }
}
