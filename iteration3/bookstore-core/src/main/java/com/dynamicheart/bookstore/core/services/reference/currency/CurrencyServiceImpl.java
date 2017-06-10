package com.dynamicheart.bookstore.core.services.reference.currency;

import com.dynamicheart.bookstore.core.model.reference.currency.Currency;
import com.dynamicheart.bookstore.core.repositories.reference.currency.CurrencyRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceJpaImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("currencyService")
public class CurrencyServiceImpl extends BookstoreEntityServiceJpaImpl<Long, Currency>
	implements CurrencyService {
	
	private CurrencyRepository currencyRepository;
	
	@Inject
	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		super(currencyRepository);
		this.currencyRepository = currencyRepository;
	}

	@Override
	public Currency getByCode(String code) {
		return currencyRepository.getByCode(code);
	}

}
