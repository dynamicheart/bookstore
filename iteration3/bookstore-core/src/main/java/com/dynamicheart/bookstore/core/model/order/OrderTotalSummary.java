package com.dynamicheart.bookstore.core.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderTotalSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal subTotal;//one time price for items
	private BigDecimal total;//final price
	
	private List<OrderTotal> totals;

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderTotal> getTotals() {
		return totals;
	}

	public void setTotals(List<OrderTotal> totals) {
		this.totals = totals;
	}

}
