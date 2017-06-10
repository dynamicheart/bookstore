package com.dynamicheart.bookstore.core.model.catalog.book.description;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_DESCRIPTION", schema= SchemaConstant.BOOKSTORE_SCHEMA, uniqueConstraints={
	@UniqueConstraint(columnNames={
			"BOOK_ID",
            "LANGUAGE_ID"
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

    @Column(name = "META_TITLE")
    private String metatagTitle;

    @Column(name = "META_KEYWORDS")
    private String metatagKeywords;

    @Column(name = "META_DESCRIPTION")
    private String metatagDescription;

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
}
