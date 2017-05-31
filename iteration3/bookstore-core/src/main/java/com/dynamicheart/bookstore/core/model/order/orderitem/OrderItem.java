package com.dynamicheart.bookstore.core.model.order.orderitem;



import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import com.dynamicheart.bookstore.core.model.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ORDER_ITEM" , schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class OrderItem extends BookstoreEntity<Long, OrderItem> {
	private static final long serialVersionUID = 176131742783954627L;
	
	@Id
	@Column(name="ORDER_ITEM_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_ITEM_ID_NEXT_VALUE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@Column(name="ITEM_ISBN")
	private String isbn;

	@Column(name="ITEM_NAME" , length=64 , nullable=false)
	private String itemName;

	@Column(name="ITEM_QUANTITY")
	private int itemQuantity;

	@Column(name="ONETIME_CHARGE" , nullable=false )
	private BigDecimal oneTimeCharge;

	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private Order order;
	
	@OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
	private Set<OrderItemPrice> prices = new HashSet<OrderItemPrice>();
	
	
	public OrderItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}



	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

	public Set<OrderItemPrice> getPrices() {
		return prices;
	}

	public void setPrices(Set<OrderItemPrice> prices) {
		this.prices = prices;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setOneTimeCharge(BigDecimal oneTimeCharge) {
		this.oneTimeCharge = oneTimeCharge;
	}

	public BigDecimal getOneTimeCharge() {
		return oneTimeCharge;
	}
	
}
