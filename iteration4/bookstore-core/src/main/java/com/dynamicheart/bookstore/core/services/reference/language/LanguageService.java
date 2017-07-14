package com.dynamicheart.bookstore.core.services.reference.language;


import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface LanguageService extends BookstoreEntityService<Integer, Language> {

	Language getByCode(String code) throws ServiceException;

	Map<String, Language> getLanguagesMap() throws ServiceException;

	List<Language> getLanguages() throws ServiceException;

	Locale toLocale(Language language);

	Language toLanguage(Locale locale);
	
	Language defaultLanguage();
}
