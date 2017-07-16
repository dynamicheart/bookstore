package com.dynamicheart.bookstore.store.utils;

import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class LanguageUtils {
	
	@Inject
	LanguageService languageService;
	

	public Language getRequestLanguage(HttpServletRequest request, HttpServletResponse response) {
		
		Locale locale = null;
		
		Language language = (Language) request.getSession().getAttribute(StoreConstants.LANGUAGE);
		

		if(language==null) {
			try {
                language = languageService.defaultLanguage();
			    locale = languageService.toLocale(language);
                if(locale!=null) {
                    LocaleContextHolder.setLocale(locale);
                }
                request.getSession().setAttribute(StoreConstants.LANGUAGE, language);
			} catch(Exception e) {
				if(language==null) {
					try {
						language = languageService.getByCode(StoreConstants.DEFAULT_LANGUAGE);
					} catch(Exception ignore) {}
				}
			}
		} else {
			Locale localeFromContext = LocaleContextHolder.getLocale();//should be browser locale
			if(!language.getCode().equals(localeFromContext.getLanguage())) {
				//get locale context
				language = languageService.toLanguage(localeFromContext);
			}

		}
		
		if(language != null) {
			locale = languageService.toLocale(language);
		} else {
			language = languageService.toLanguage(locale);
		}
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if(localeResolver!=null) {
			localeResolver.setLocale(request, response, locale);
		}
		response.setLocale(locale);

		return language;
	}
	
	/**
	 * Should be used by rest web services
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Language getRESTLanguage(HttpServletRequest request) throws Exception {
		
		Validate.notNull(request,"HttpServletRequest must not be null");

		Language language = null;
		
		
		String lang = request.getParameter(StoreConstants.LANG);
		
		if(StringUtils.isBlank(lang)) {
			//try with HttpSession
			language = (Language) request.getSession().getAttribute(StoreConstants.LANGUAGE);
			
			if(language==null) {
				language = languageService.defaultLanguage();
			}
		} else {
			language = languageService.getByCode(lang);
			if(language==null) {
				language = (Language) request.getSession().getAttribute(StoreConstants.LANGUAGE);
				
				if(language==null) {
					language = languageService.defaultLanguage();
				}
			}
		}

		return language;
	}

}
