package com.dynamicheart.bookstore.core.services.reference.init;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.services.user.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("initializationDatabase")
public class InitializationDatabaseImpl implements InitializationDatabase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitializationDatabaseImpl.class);

	@Inject
	PermissionService permissionService;
	
	private String name;
	
	public boolean isEmpty() {
		return permissionService.count() == 0;
	}
	
	@Transactional
	public void populate(String contextName) throws ServiceException {
		this.name =  contextName;
	}

	

	



}
