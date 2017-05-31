package com.dynamicheart.bookstore.core.model.order.orderitem;



import com.dynamicheart.bookstore.core.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ORDER_ITEM_PRICE" , schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class OrderItemPrice implements Serializable {
	
	private static final long serialVersionUID = 6931547221513094745L;
	
	@Id
	@Column(name="ORDER_ITEM_PRICE_ID")
	@TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
		pkColumnValue = "ORDER_PRD_PRICE_ID_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ORDER_ITEM_ID", nullable = false)
	private OrderItem orderItem;


	@Column(name = "ITEM_PRICE_CODE", nullable = false , length=64 )
	private String itemPriceCode;

	@Column(name = "ITEM_PRICE", nullable = false)
	private BigDecimal itemPrice;
	
	@Column(name = "ITEM_PRICE_SPECIAL")
	private BigDecimal itemPriceSpecial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PRD_PRICE_SPECIAL_ST_DT" , length=0)
	private Date itemPriceSpecialStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PRD_PRICE_SPECIAL_END_DT" , length=0)
	private Date itemPriceSpecialEndDate;


	@Column(name = "DEFAULT_PRICE", nullable = false)
	private Boolean defaultPrice;


	@Column(name = "ITEM_PRICE_NAME", nullable = true)
	private String itemPriceName;
	
	public OrderItemPrice() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Boolean defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public String getItemPriceName() {
		return itemPriceName;
	}

	public void setItemPriceName(String itemPriceName) {
		this.itemPriceName = itemPriceName;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public void setItemPriceCode(String itemPriceCode) {
		this.itemPriceCode = itemPriceCode;
	}

	public String getItemPriceCode() {
		return itemPriceCode;
	}

	public void setItemPriceSpecialStartDate(
			Date itemPriceSpecialStartDate) {
		this.itemPriceSpecialStartDate = itemPriceSpecialStartDate;
	}

	public Date getItemPriceSpecialStartDate() {
		return itemPriceSpecialStartDate;
	}

	public void setItemPriceSpecialEndDate(Date itemPriceSpecialEndDate) {
		this.itemPriceSpecialEndDate = itemPriceSpecialEndDate;
	}

	public Date getItemPriceSpecialEndDate() {
		return itemPriceSpecialEndDate;
	}

	public void setItemPriceSpecial(BigDecimal itemPriceSpecial) {
		this.itemPriceSpecial = itemPriceSpecial;
	}

	public BigDecimal getItemPriceSpecial() {
		return itemPriceSpecial;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}
}
