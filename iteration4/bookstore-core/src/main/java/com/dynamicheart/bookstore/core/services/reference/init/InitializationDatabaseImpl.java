package com.dynamicheart.bookstore.core.services.reference.init;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.reference.currency.Currency;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.reference.currency.CurrencyService;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("initializationDatabase")
public class InitializationDatabaseImpl implements InitializationDatabase {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitializationDatabaseImpl.class);

	@Inject
	private LanguageService languageService;

	@Inject
	private CurrencyService currencyService;

	private String name;

	public boolean isEmpty() {
		return languageService.count() == 0;
	}

	@Transactional
	public void populate(String contextName) throws ServiceException {
		this.name =  contextName;
		createLanguages();
		createCurrencies();
	}

	private void createCurrencies() throws ServiceException {
		LOGGER.info(String.format("%s : Populating Currencies ", name));

		for (String code : SchemaConstant.CURRENCY_MAP.keySet()) {
			try {
				java.util.Currency c = java.util.Currency.getInstance(code);

				if(c==null) {
					LOGGER.info(String.format("%s : Populating Currencies : no currency for code : %s", name, code));
				}
				Currency currency = new Currency();
				currency.setName(c.getCurrencyCode());
				currency.setCurrency(c);
				currencyService.create(currency);
			} catch (IllegalArgumentException e) {
				LOGGER.info(String.format("%s : Populating Currencies : no currency for code : %s", name, code));
			}
		}
	}

	private void createLanguages() throws ServiceException {
		LOGGER.info(String.format("%s : Populating Languages ", name));
		for(String code : SchemaConstant.LANGUAGE_ISO_CODE) {
			Language language = new Language(code);
			languageService.create(language);
		}
	}
}
