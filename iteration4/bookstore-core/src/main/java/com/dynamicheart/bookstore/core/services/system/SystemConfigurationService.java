package com.dynamicheart.bookstore.core.services.system;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.system.SystemConfiguration;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

public interface SystemConfigurationService extends
		BookstoreEntityService<Long, SystemConfiguration> {
	
	SystemConfiguration getByKey(String key) throws ServiceException;

}
