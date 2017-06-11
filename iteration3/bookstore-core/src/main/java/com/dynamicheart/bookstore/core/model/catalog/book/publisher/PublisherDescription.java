package com.dynamicheart.bookstore.core.model.catalog.book.publisher;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.Description;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PUBLISHER_DESCRIPTION", schema= SchemaConstant.BOOKSTORE_SCHEMA, uniqueConstraints={
	@UniqueConstraint(columnNames={
			"PUBLISHER_ID"
		})
	}
)
public class PublisherDescription extends Description {
	private static final long serialVersionUID = -2164581613773995282L;
	
	@ManyToOne(targetEntity = Publisher.class)
	@JoinColumn(name = "PUBLISHER_ID", nullable = false)
	private Publisher publisher;
	
	@Column(name = "PUBLISHER_URL")
	private String url;
	
	public PublisherDescription() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
