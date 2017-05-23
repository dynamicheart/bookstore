package com.dynamicheart.bookstore.core.model.common;

import java.io.Serializable;

public class EntityList implements Serializable {


	private static final long serialVersionUID = 607516654546543664L;

	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
