package com.dynamicheart.bookstore.core.model.catalog.book.price;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.Description;

import javax.persistence.*;

@Entity
@Table(name="BOOK_PRICE_DESCRIPTION", schema= SchemaConstant.BOOKSTORE_SCHEMA, uniqueConstraints={
		@UniqueConstraint(columnNames={
			"BOOK_PRICE_ID"
		})
	}
)
public class BookPriceDescription extends Description {
	private static final long serialVersionUID = 4484271953328047362L;

	public final static String DEFAULT_PRICE_DESCRIPTION = "DEFAULT";

	@ManyToOne(targetEntity = BookPrice.class)
	@JoinColumn(name = "BOOK_PRICE_ID", nullable = false)
	private BookPrice bookPrice;
	
	public BookPriceDescription() {
	}

	public BookPrice getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BookPrice bookPrice) {
		this.bookPrice = bookPrice;
	}


}
