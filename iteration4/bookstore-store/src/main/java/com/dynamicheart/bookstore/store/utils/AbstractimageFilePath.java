package com.dynamicheart.bookstore.store.utils;


import javax.annotation.Resource;
import java.util.Properties;


public abstract class AbstractimageFilePath implements ImageFilePath {


	public abstract String getBasePath();

	public abstract void setBasePath(String basePath);
	
	protected static final String CONTEXT_PATH = "CONTEXT_PATH";
	
	public @Resource(name="bookstore-properties") Properties properties = new Properties();//bookstore-properties

	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}


}
