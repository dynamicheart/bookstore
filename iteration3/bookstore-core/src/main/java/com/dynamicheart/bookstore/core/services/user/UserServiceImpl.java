package com.dynamicheart.bookstore.core.services.user;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.user.User;
import com.dynamicheart.bookstore.core.repositories.user.UserRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceJpaImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends BookstoreEntityServiceJpaImpl<Long, User>
		implements UserService {


	private UserRepository userRepository;
	
	@Inject
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;

	}
	
	@Override
	public User getByUserName(String userName) throws ServiceException {
		
		return userRepository.findByUserName(userName);
		
	}
	
	@Override
	public void delete(User user) throws ServiceException {
		
		User u = this.getById(user.getId());
		super.delete(u);
		
	}

	@Override
	public List<User> listUser() throws ServiceException {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void saveOrUpdate(User user) throws ServiceException {

		userRepository.save(user);
		
	}

}
