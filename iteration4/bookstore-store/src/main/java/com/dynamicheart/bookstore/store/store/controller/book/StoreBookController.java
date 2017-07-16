package com.dynamicheart.bookstore.store.store.controller.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.utils.CacheUtils;
import com.dynamicheart.bookstore.store.store.model.AjaxResponse;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.store.populator.catalog.ReadableBookPopulator;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ReadableBookPopulator populator;

    @RequestMapping("/{friendlyUrl}")
    public ResponseEntity<AjaxResponse> displayBook(@PathVariable final String friendlyUrl, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

        Language language = (Language)request.getAttribute("LANGUAGE");

        Book book = bookService.getBySeUrl(friendlyUrl, locale);


        ReadableBook bookProxy = populator.populate(book, new ReadableBook(), language);

        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
        ajaxResponse.getResult().put("book",bookProxy);

        return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
    }
    
}
