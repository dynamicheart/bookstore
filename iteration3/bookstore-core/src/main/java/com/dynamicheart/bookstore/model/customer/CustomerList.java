package com.dynamicheart.bookstore.model.customer;



import com.dynamicheart.bookstore.model.common.EntityList;

import java.util.List;

public class CustomerList extends EntityList {


	private static final long serialVersionUID = 3712228030296430960L;

	private List<Customer> customers;
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<Customer> getCustomers() {
		return customers;
	}

}
