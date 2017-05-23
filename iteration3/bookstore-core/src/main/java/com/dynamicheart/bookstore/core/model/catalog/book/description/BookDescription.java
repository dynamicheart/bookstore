package com.dynamicheart.bookstore.core.model.catalog.book.description;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.common.Description;

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

	@OneToOne(targetEntity = Book.class)
	@JoinColumn(name = "BOOK_ID", nullable = false)
	private Book book;

	@Column(name = "SEF_URL")
	private String seUrl;


	public BookDescription() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getSeUrl() {
		return seUrl;
	}

	public void setSeUrl(String seUrl) {
		this.seUrl = seUrl;
	}


}
