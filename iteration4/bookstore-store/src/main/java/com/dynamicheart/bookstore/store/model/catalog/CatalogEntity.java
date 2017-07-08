package com.dynamicheart.bookstore.store.model.catalog;

import com.dynamicheart.bookstore.store.model.StoreEntity;

import java.io.Serializable;


public abstract class CatalogEntity extends StoreEntity implements Serializable {


	private static final long serialVersionUID = 8337083768081322462L;

	private String name;

	private String description;

	private String friendlyUrl;

	private String title;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
