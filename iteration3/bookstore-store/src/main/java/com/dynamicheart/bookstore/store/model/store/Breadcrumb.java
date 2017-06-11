package com.dynamicheart.bookstore.store.model.store;


import com.dynamicheart.bookstore.core.model.reference.language.Language;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Breadcrumb implements Serializable {

	private static final long serialVersionUID = 1L;
	private BreadcrumbItemType itemType;
	private Language language;
	private String urlRefContent = null;
	private List<BreadcrumbItem> breadCrumbs = new ArrayList<BreadcrumbItem>();
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<BreadcrumbItem> getBreadCrumbs() {
		return breadCrumbs;
	}
	public void setBreadCrumbs(List<BreadcrumbItem> breadCrumbs) {
		this.breadCrumbs = breadCrumbs;
	}
	public void setItemType(BreadcrumbItemType itemType) {
		this.itemType = itemType;
	}
	public BreadcrumbItemType getItemType() {
		return itemType;
	}
	public String getUrlRefContent() {
		return urlRefContent;
	}
	public void setUrlRefContent(String urlRefContent) {
		this.urlRefContent = urlRefContent;
	}

}
