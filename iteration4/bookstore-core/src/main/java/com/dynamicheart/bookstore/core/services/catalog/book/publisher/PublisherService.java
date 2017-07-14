package com.dynamicheart.bookstore.core.services.catalog.book.publisher;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.PublisherDescription;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

public interface PublisherService extends BookstoreEntityService<Long, Publisher> {


	void saveOrUpdate(Publisher publisher) throws ServiceException;
	
	void addPublisherDescription(Publisher publisher, PublisherDescription description) throws ServiceException;
	
	Long getCountManufAttachedProducts(Publisher publisher)  throws ServiceException;
	
	void delete(Publisher publisher) throws ServiceException;
	
	Publisher getByCode(String code);

	
}
