package com.dynamicheart.bookstore.store.store.model.catalog.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadableCategory extends CategoryEntity implements Serializable {


	private static final long serialVersionUID = -6945354245243350377L;

	private CategoryDescription description;//one category based on language

	//private ReadableCategory parent;

	private int bookCount;

	private List<ReadableCategory> children = new ArrayList<ReadableCategory>();
	
	
	public void setDescription(CategoryDescription description) {
		this.description = description;
	}
	public CategoryDescription getDescription() {
		return description;
	}

	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public List<ReadableCategory> getChildren() {
		return children;
	}
	public void setChildren(List<ReadableCategory> children) {
		this.children = children;
	}

}
