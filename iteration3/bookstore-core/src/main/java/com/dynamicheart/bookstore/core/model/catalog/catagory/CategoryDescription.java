package com.dynamicheart.bookstore.core.model.catalog.catagory;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.Description;
import com.dynamicheart.bookstore.core.model.reference.language.Language;

import javax.persistence.*;


@Entity
@Table(name="CATEGORY_DESCRIPTION", schema= SchemaConstant.BOOKSTORE_SCHEMA,uniqueConstraints={
		@UniqueConstraint(columnNames={
			"CATEGORY_ID",
			"LANGUAGE_ID"
		})
	}
)
public class CategoryDescription extends Description {
	private static final long serialVersionUID = -3248423008455359301L;
	

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	@Column(name="SEF_URL", length=120)
	private String seUrl;
	
	@Column(name = "CATEGORY_HIGHLIGHT")
	private String categoryHighlight;

	public String getCategoryHighlight() {
		return categoryHighlight;
	}

	public void setCategoryHighlight(String categoryHighlight) {
		this.categoryHighlight = categoryHighlight;
	}

	@Column(name="META_TITLE", length=120)
	private String metatagTitle;
	
	@Column(name="META_KEYWORDS")
	private String metatagKeywords;
	
	@Column(name="META_DESCRIPTION")
	private String metatagDescription;
	
	public CategoryDescription() {
	}
	
	public CategoryDescription(String name, Language language) {
		this.setName(name);
		this.setLanguage(language);
		super.setId(0L);
	}
	
	public String getSeUrl() {
		return seUrl;
	}

	public void setSeUrl(String seUrl) {
		this.seUrl = seUrl;
	}

	public String getMetatagTitle() {
		return metatagTitle;
	}

	public void setMetatagTitle(String metatagTitle) {
		this.metatagTitle = metatagTitle;
	}

	public String getMetatagKeywords() {
		return metatagKeywords;
	}

	public void setMetatagKeywords(String metatagKeywords) {
		this.metatagKeywords = metatagKeywords;
	}

	public String getMetatagDescription() {
		return metatagDescription;
	}

	public void setMetatagDescription(String metatagDescription) {
		this.metatagDescription = metatagDescription;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
