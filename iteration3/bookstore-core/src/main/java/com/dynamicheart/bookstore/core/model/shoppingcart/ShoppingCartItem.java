package com.dynamicheart.bookstore.core.model.shoppingcart;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.price.FinalPrice;
import com.dynamicheart.bookstore.core.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.core.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.core.model.common.audit.Auditable;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SHOPPING_CART_ITEM", schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class ShoppingCartItem extends BookstoreEntity<Long, ShoppingCartItem> implements Auditable, Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SHP_CART_ITEM_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHP_CRT_ITM_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@ManyToOne(targetEntity = ShoppingCart.class)
	@JoinColumn(name = "SHP_CART_ID", nullable = false)
	private ShoppingCart shoppingCart;

	@Column(name="QUANTITY")
	private Integer quantity = new Integer(1);

	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Column(name="BOOK_ID", nullable=false)
	private Long bookId;
	
	@Transient
	private BigDecimal itemPrice;//item final price including all rebates
	
	@Transient
	private BigDecimal subTotal;//item final price * quantity
	
	@Transient
	private FinalPrice finalPrice;//contains price details (raw prices)

	@Transient
	private Book book;
	
	@Transient
	private boolean obsolete = false;


	public ShoppingCartItem(ShoppingCart shoppingCart, Book book) {
		this.book = book;
		this.bookId = book.getId();
		this.quantity = 1;
		this.shoppingCart = shoppingCart;
		
	}
	
	public ShoppingCartItem(Book book) {
		this.book = book;
		this.bookId = book.getId();
		this.quantity = 1;

	}
	
	public ShoppingCartItem() {
		
	}

	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		this.auditSection = audit;
		
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}



	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}



	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}


	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setFinalPrice(FinalPrice finalPrice) {
		this.finalPrice = finalPrice;
	}

	public FinalPrice getFinalPrice() {
		return finalPrice;
	}
	
	public boolean isObsolete() {
		return obsolete;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

}
