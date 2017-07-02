package com.dynamicheart.bookstore.core.services.catalog.book;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.currency.Currency;
import com.dynamicheart.bookstore.core.utils.BookAvailabilityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Locale;


@Service("pricingService")
public class PricingServiceImpl implements PricingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PricingServiceImpl.class);

	@Inject
	private BookAvailabilityUtils priceUtil;
	
	@Override
	public BigDecimal calculateBookPrice(Book book) throws ServiceException {
		return priceUtil.getPrice(book);
	}
	
	@Override
	public BigDecimal calculateBookPrice(Book book, Customer customer) throws ServiceException {
		return priceUtil.getPrice(book);
	}
	
	@Override
	public String getDisplayAmount(BigDecimal amount, Locale locale,
								   Currency currency) throws ServiceException {
		try {
			String price= priceUtil.getFormatedAmountWithCurrency(locale, currency, amount);
			return price;
		} catch (Exception e) {
			LOGGER.error("An error occured when trying to format an amunt " + amount.toString() + " using locale " + locale.toString() + " and currency " + currency.toString());
			throw new ServiceException(e);
		}
	}

	@Override
	public String getStringAmount(BigDecimal amount)
			throws ServiceException {
		try {
			String price = priceUtil.getAdminFormatedAmount(amount);
			return price;
		} catch (Exception e) {
			LOGGER.error("An error occured when trying to format an amount " + amount.toString());
			throw new ServiceException(e);
		}
	}



}
