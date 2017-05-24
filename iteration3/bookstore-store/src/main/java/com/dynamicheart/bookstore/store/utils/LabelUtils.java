package com.dynamicheart.bookstore.store.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

public class LabelUtils implements ApplicationContextAware {

	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}
	
	public String getMessage(String key) {
		return applicationContext.getMessage(key, null,Locale.getDefault());
	}
	
	public String getMessage(String key, String defaultValue) {
		try {
			return applicationContext.getMessage(key, null,Locale.getDefault());
		} catch(Exception ignore) {}
		return defaultValue;
	}
	
	public String getMessage(String key, String[] args) {
		return applicationContext.getMessage(key, args, Locale.getDefault());
	}

}
