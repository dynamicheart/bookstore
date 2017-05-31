package com.dynamicheart.bookstore.core.services.user;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.user.User;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;


public interface UserService extends BookstoreEntityService<Long, User> {

	User getByUserName(String userName) throws ServiceException;

	List<User> listUser() throws ServiceException;
	
	/**
	 * Create or update a User
	 * @param user
	 * @throws ServiceException
	 */
	void saveOrUpdate(User user) throws ServiceException;

}
