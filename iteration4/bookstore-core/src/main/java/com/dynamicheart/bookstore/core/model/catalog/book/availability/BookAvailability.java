package com.dynamicheart.bookstore.core.model.catalog.book.availability;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name="BOOK_AVAILABILITY", schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class BookAvailability extends BookstoreEntity<Long, BookAvailability> {

	private static final long serialVersionUID = -138784819383816157L;

	@Id
	@Column(name = "BOOK_AVAIL_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "BOOK_AVAIL_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@ManyToOne(targetEntity = Book.class)
	@JoinColumn(name = "BOOK_ID", nullable = false)
	private Book book;

	@NotNull
	@Column(name="QUANTITY")
	private Integer bookQuantity = 0;

	@NotNull
	@Column(name = "PRICE")
	private BigDecimal bookPrice = new BigDecimal(0);

	@Column(name="REGION")
	private String region = SchemaConstant.ALL_REGIONS;


	public BookAvailability() {
	}

	public Integer getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(Integer bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
