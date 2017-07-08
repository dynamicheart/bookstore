package com.dynamicheart.bookstore.store.store.controller;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.PricingService;
import com.dynamicheart.bookstore.store.constants.Constants;
import com.dynamicheart.bookstore.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.model.catalog.book.ReadableBookList;
import com.dynamicheart.bookstore.store.model.store.Breadcrumb;
import com.dynamicheart.bookstore.store.model.store.BreadcrumbItem;
import com.dynamicheart.bookstore.store.model.store.BreadcrumbItemType;
import com.dynamicheart.bookstore.store.populator.catalog.ReadableBookPopulator;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class HomeController {
	
	@Inject
	private LabelUtils messages;

	@Inject
	private BookService bookService;

	@Inject
	private PricingService pricingService;

	@Inject
	private ImageFilePath imageUtils;

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	private final static String HOME_LINK_CODE="HOME";
	
	@RequestMapping(value={Constants.STORE_URI + "/home",Constants.STORE_URI +"/", Constants.STORE_URI}, method= RequestMethod.GET)
	public String displayLanding(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		Language language = (Language)request.getAttribute(Constants.LANGUAGE);


		List<Book> books = bookService.list();

        ReadableBookPopulator populator = new ReadableBookPopulator();
        populator.setPricingService(pricingService);
        populator.setImageUtils(imageUtils);

        ReadableBookList bookList = new ReadableBookList();
        for(Book book:books){
            ReadableBook b = populator.populate(book, new ReadableBook(), language);
            bookList.getBooks().add(b);
        }

        model.addAttribute("books",bookList);

		return "store-home";
	}
}
