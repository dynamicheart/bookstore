package com.dynamicheart.bookstore.store.filter;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.utils.CacheUtils;
import com.dynamicheart.bookstore.core.utils.CoreConfiguration;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import com.dynamicheart.bookstore.store.utils.LanguageUtils;
import com.dynamicheart.bookstore.store.utils.WebApplicationCacheUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class StoreFilter extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreFilter.class);

	private final static String STORE_REQUEST_PARAMETER = "store";

	@Inject
	private CategoryService categoryService;

	@Inject
	private BookService bookService;

	@Inject
	private CustomerService customerService;

	@Inject
	private LanguageService languageService;

	@Inject
	private LabelUtils messages;

	@Inject
	private LanguageUtils languageUtils;

	@Inject
	private CacheUtils cache;

	@Inject
	private WebApplicationCacheUtils webApplicationCache;

	@Inject
	private CoreConfiguration coreConfiguration;

	private final static String SERVICES_URL_PATTERN = "/services";
	private final static String REFERENCE_URL_PATTERN = "/reference";


    public StoreFilter() {
    }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");

		if(request.getRequestURL().toString().toLowerCase().contains(SERVICES_URL_PATTERN) || request.getRequestURL().toString().toLowerCase().contains(REFERENCE_URL_PATTERN)) {
			return true;
		}

		try {
			/** customer **/
			Customer customer = (Customer)request.getSession().getAttribute(StoreConstants.CUSTOMER);
			if(customer!=null) {
				request.setAttribute(StoreConstants.CUSTOMER, customer);
			}

			if(customer==null) {

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if(auth != null &&
						request.isUserInRole("AUTH_CUSTOMER")) {
					customer = customerService.getByNick(auth.getName());
					if(customer!=null) {
						request.setAttribute(StoreConstants.CUSTOMER, customer);
					}
				}

			}

			/** language & locale **/
			Language language = languageUtils.getRequestLanguage(request, response);
			request.setAttribute(StoreConstants.LANGUAGE, language);


			Locale locale = languageService.toLocale(language);

			//Locale locale = LocaleContextHolder.getLocale();
			LocaleContextHolder.setLocale(locale);
		}catch (Exception e){
			LOGGER.error("Error in StoreFilter",e);
		}
    	return true;
	}
}
