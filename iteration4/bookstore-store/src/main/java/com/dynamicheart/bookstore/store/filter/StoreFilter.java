package com.dynamicheart.bookstore.store.filter;

import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.utils.CacheUtils;
import com.dynamicheart.bookstore.core.utils.CoreConfiguration;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import com.dynamicheart.bookstore.store.utils.LanguageUtils;
import com.dynamicheart.bookstore.store.utils.WebApplicationCacheUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;


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
}
