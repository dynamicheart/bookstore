package com.dynamicheart.bookstore.core.services.reference.init;


import com.dynamicheart.bookstore.core.utils.exception.ServiceException;

public interface InitializationDatabase {
	
	boolean isEmpty();
	
	void populate(String name) throws ServiceException;

}
