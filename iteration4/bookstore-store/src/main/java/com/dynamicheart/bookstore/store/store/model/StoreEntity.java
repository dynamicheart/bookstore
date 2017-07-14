package com.dynamicheart.bookstore.store.store.model;

import java.io.Serializable;

public abstract class StoreEntity extends Entity implements Serializable {

	private static final long serialVersionUID = -5234849908789160166L;

	private String language;
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}


}
