package com.dynamicheart.bookstore.core.model.catalog.category;


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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
