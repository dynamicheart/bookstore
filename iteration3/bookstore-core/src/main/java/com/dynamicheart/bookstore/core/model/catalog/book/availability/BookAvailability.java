package com.dynamicheart.bookstore.core.model.catalog.book.availability;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.price.BookPrice;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import com.dynamicheart.bookstore.core.utils.CloneUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_AVAILABLE")
	private Date bookDateAvailable;

	@Column(name="REGION")
	private String region = SchemaConstant.ALL_REGIONS;

	@Column(name="STATUS")
	private boolean bookStatus = true;

	@Column(name="QUANTITY_ORD_MIN")
	private Integer bookQuantityOrderMin = 0;

	@Column(name="QUANTITY_ORD_MAX")
	private Integer bookQuantityOrderMax = 0;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="bookAvailability", cascade = CascadeType.ALL)
	private Set<BookPrice> prices = new HashSet<BookPrice>();

	@Transient
	public BookPrice defaultPrice() {

		for(BookPrice price : prices) {
			if(price.isDefaultPrice()) {
				return price;
			}
		}
		return new BookPrice();
	}

	public BookAvailability() {
	}

	public Integer getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(Integer bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	public Date getBookDateAvailable() {
		return CloneUtils.clone(bookDateAvailable);
	}

	public void setBookDateAvailable(Date bookDateAvailable) {
		this.bookDateAvailable = CloneUtils.clone(bookDateAvailable);
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(boolean bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Integer getBookQuantityOrderMin() {
		return bookQuantityOrderMin;
	}

	public void setBookQuantityOrderMin(Integer bookQuantityOrderMin) {
		this.bookQuantityOrderMin = bookQuantityOrderMin;
	}

	public Integer getBookQuantityOrderMax() {
		return bookQuantityOrderMax;
	}

	public void setBookQuantityOrderMax(Integer bookQuantityOrderMax) {
		this.bookQuantityOrderMax = bookQuantityOrderMax;
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



	public Set<BookPrice> getPrices() {
		return prices;
	}

	public void setPrices(Set<BookPrice> prices) {
		this.prices = prices;
	}

}
