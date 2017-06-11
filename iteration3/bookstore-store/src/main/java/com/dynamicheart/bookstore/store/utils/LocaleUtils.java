package com.dynamicheart.bookstore.store.utils;

import com.dynamicheart.bookstore.core.model.reference.language.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;


public class LocaleUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocaleUtils.class);
	
	public static Locale getLocale(Language language) {
		
		return new Locale(language.getCode());
		
	}
}
