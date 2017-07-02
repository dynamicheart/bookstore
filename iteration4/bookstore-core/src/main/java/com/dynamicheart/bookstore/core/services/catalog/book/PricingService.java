package com.dynamicheart.bookstore.core.services.catalog.book;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.currency.Currency;

import java.math.BigDecimal;
import java.util.Locale;

public interface PricingService {

	BigDecimal calculateBookPrice(Book book) throws ServiceException;

	BigDecimal calculateBookPrice(Book book, Customer customer)
			throws ServiceException;

	String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency)
			throws ServiceException;

	String getStringAmount(BigDecimal amount)
			throws ServiceException;
}
