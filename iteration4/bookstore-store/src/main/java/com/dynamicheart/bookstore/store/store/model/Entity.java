package com.dynamicheart.bookstore.store.store.model;

import java.io.Serializable;

public class Entity implements Serializable {

	private static final long serialVersionUID = -5614731496334464731L;

	private Long id = 0L;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
