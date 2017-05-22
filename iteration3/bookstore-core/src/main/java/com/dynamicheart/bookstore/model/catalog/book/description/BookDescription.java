package com.dynamicheart.bookstore.model.catalog.book.description;


import com.dynamicheart.bookstore.constants.SchemaConstant;
import com.dynamicheart.bookstore.model.catalog.book.Book;
import com.dynamicheart.bookstore.model.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_DESCRIPTION", schema= SchemaConstant.BOOKSTORE_SHECMA, uniqueConstraints={
	@UniqueConstraint(columnNames={
			"BOOK_ID",
		})
	}
)
public class BookDescription extends Description {
	private static final long serialVersionUID = 7007939137361544460L;

	@ManyToOne(targetEntity = Book.class)
	@JoinColumn(name = "BOOK_ID", nullable = false)
	private Book book;

	@Column(name = "SEF_URL")
	private String seUrl;


	public BookDescription() {
	}


	public String getSeUrl() {
		return seUrl;
	}

	public void setSeUrl(String seUrl) {
		this.seUrl = seUrl;
	}


}
