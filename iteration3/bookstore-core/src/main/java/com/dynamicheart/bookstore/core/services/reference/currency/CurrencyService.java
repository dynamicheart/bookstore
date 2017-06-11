package com.dynamicheart.bookstore.core.services.reference.currency;


import com.dynamicheart.bookstore.core.model.reference.currency.Currency;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

public interface CurrencyService extends BookstoreEntityService<Long, Currency> {

	Currency getByCode(String code);

}
