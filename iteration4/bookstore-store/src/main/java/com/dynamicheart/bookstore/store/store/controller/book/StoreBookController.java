package com.dynamicheart.bookstore.store.store.controller.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.PricingService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.utils.CacheUtils;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.store.populator.catalog.ReadableBookPopulator;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Controller
@RequestMapping("/store/book")
public class StoreBookController {
    
    @Inject
    private BookService bookService;
    
    @Inject
    private PricingService pricingService;
    
    @Inject
    private LabelUtils messages;

    @Inject
    private CacheUtils cache;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ImageFilePath imageUtils;

    @RequestMapping("/{friendlyUrl}")
    public String displayBook(@PathVariable final String friendlyUrl, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
        return display(null, friendlyUrl, model, request, response, locale);
    }

    public String display(final String reference, final String friendlyUrl, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
        
        Language language = (Language)request.getAttribute("LANGUAGE");

        Book book = bookService.getBySeUrl(friendlyUrl, locale);

//        if(book==null) {
//            return PageBuilderUtils.build404();
//        }

        ReadableBookPopulator populator = new ReadableBookPopulator();
        populator.setPricingService(pricingService);
        populator.setImageUtils(imageUtils);

        ReadableBook bookProxy = populator.populate(book, new ReadableBook(), language);

        model.addAttribute("book", bookProxy);


        return "book";
    }
    
}
