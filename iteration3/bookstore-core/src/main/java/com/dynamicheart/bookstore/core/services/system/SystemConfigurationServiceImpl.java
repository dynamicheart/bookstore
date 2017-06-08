package com.dynamicheart.bookstore.core.services.system;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.system.SystemConfiguration;
import com.dynamicheart.bookstore.core.repositories.system.SystemConfigurationRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceJpaImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl extends
		BookstoreEntityServiceJpaImpl<Long, SystemConfiguration> implements
		SystemConfigurationService {

	
	private SystemConfigurationRepository systemConfigurationReposotory;
	
	@Inject
	public SystemConfigurationServiceImpl(
			SystemConfigurationRepository systemConfigurationReposotory) {
			super(systemConfigurationReposotory);
			this.systemConfigurationReposotory = systemConfigurationReposotory;
	}
	
	public SystemConfiguration getByKey(String key) throws ServiceException {
		return systemConfigurationReposotory.findByKey(key);
	}
	



}
