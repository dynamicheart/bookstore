package com.dynamicheart.bookstore.store.store.controller.search;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.store.controller.AbstractController;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableBookList;
import com.dynamicheart.bookstore.store.store.populator.catalog.ReadableBookPopulator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by dynamicheart on 7/15/2017.
 */

@Controller
@RequestMapping("/store/search")
public class SearchController extends AbstractController{

    @Inject
    BookService bookService;

    @Inject
    ReadableBookPopulator populator;

    @RequestMapping(value= "/book", method= RequestMethod.GET)
    public String displayResult(Model model, @RequestParam(value = "keyword", defaultValue = "")final String criteria, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

        Language language = (Language)request.getAttribute(StoreConstants.LANGUAGE);

        List<Book> books = bookService.getBooksByCriteria(criteria);

        ReadableBookList bookList = new ReadableBookList();
        for(Book book:books){
            ReadableBook b = populator.populate(book, new ReadableBook(), language);
            bookList.getBooks().add(b);
        }


        model.addAttribute("books",bookList);

        return "search";
    }
}
