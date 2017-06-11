package com.dynamicheart.bookstore.core.model.catalog.book.price;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import com.dynamicheart.bookstore.core.utils.CloneUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK_PRICE", schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class BookPrice extends BookstoreEntity<Long, BookPrice> {
	private static final long serialVersionUID = -9186473817468772165L;
	
	private final static String DEFAULT_PRICE_CODE="base";

	@Id
	@Column(name = "BOOK_PRICE_ID")
	@TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "BOOK_PRICE_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookPrice", cascade = CascadeType.ALL)
	private Set<BookPriceDescription> descriptions = new HashSet<BookPriceDescription>();

	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name = "BOOK_PRICE_CODE", nullable=false)
	private String code = DEFAULT_PRICE_CODE;

	@Column(name = "BOOK_PRICE_AMOUNT", nullable=false)
	private BigDecimal bookPriceAmount = new BigDecimal(0);

	@Column(name = "DEFAULT_PRICE")
	private boolean defaultPrice = false;

	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_PRICE_SPECIAL_ST_DATE")
	private Date bookPriceSpecialStartDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "BOOK_PRICE_SPECIAL_END_DATE")
	private Date bookPriceSpecialEndDate;

	@Column(name = "BOOK_PRICE_SPECIAL_AMOUNT")
	private BigDecimal bookPriceSpecialAmount;
	

	@ManyToOne(targetEntity = BookAvailability.class)
	@JoinColumn(name = "BOOK_AVAIL_ID", nullable = false)
	private BookAvailability bookAvailability;
	

	public BookPrice() {
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}




	public BigDecimal getBookPriceAmount() {
		return bookPriceAmount;
	}

	public void setBookPriceAmount(BigDecimal bookPriceAmount) {
		this.bookPriceAmount = bookPriceAmount;
	}


	
	public Date getBookPriceSpecialStartDate() {
		return CloneUtils.clone(bookPriceSpecialStartDate);
	}

	public void setBookPriceSpecialStartDate(
			Date bookPriceSpecialStartDate) {
		this.bookPriceSpecialStartDate = CloneUtils.clone(bookPriceSpecialStartDate);
	}

	public Date getBookPriceSpecialEndDate() {
		return CloneUtils.clone(bookPriceSpecialEndDate);
	}

	public void setBookPriceSpecialEndDate(Date bookPriceSpecialEndDate) {
		this.bookPriceSpecialEndDate = CloneUtils.clone(bookPriceSpecialEndDate);
	}



	public BigDecimal getBookPriceSpecialAmount() {
		return bookPriceSpecialAmount;
	}

	public void setBookPriceSpecialAmount(
			BigDecimal bookPriceSpecialAmount) {
		this.bookPriceSpecialAmount = bookPriceSpecialAmount;
	}



	public Set<BookPriceDescription> getDescriptions() {
		return descriptions;
	}



	public void setDescriptions(Set<BookPriceDescription> descriptions) {
		this.descriptions = descriptions;
	}



	public boolean isDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(boolean defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public void setBookAvailability(BookAvailability bookAvailability) {
		this.bookAvailability = bookAvailability;
	}

	public BookAvailability getBookAvailability() {
		return bookAvailability;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
