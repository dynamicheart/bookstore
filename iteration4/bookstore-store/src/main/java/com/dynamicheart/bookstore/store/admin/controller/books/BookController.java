package com.dynamicheart.bookstore.store.admin.controller.books;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.publisher.PublisherService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.utils.PriceUtils;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dynamicheart on 5/29/2017.
 */

@Controller
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookService bookService;

    @Inject
    private PublisherService publisherService;

    @Inject
    private LabelUtils messages;

    @Inject
    private PriceUtils priceUtils;

    @Inject
    private CategoryService categoryService;

    @RequestMapping(value="/admin/book/detail", method= RequestMethod.GET)
    public String displayBookEdit(@RequestParam("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayBook(id, model, request, response);
    }

    @RequestMapping(value="/admin/book/create", method= RequestMethod.GET)
    public String displayBookCreate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayBook(null, model, request, response);
    }



    @RequestMapping(value="/admin/book/viewDetail", method=RequestMethod.GET)
    public String displayBookEdit(@RequestParam("isbn") String isbn, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Book dbBook = bookService.getByCode(isbn);

        Long bookId = -1L;//non existent
        if(dbBook != null) {
            bookId = dbBook.getId();
        }

        return displayBook(bookId, model,request,response);
    }


    @RequestMapping(value="/admin/books", method= RequestMethod.GET)
    public String displayBooks(Model model, HttpServletRequest request) throws Exception {

        this.setMenu(model, request);

        return "admin-books";
    }

    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("Book", "Book");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");

        model.addAttribute("activeMenus",activeMenus);
    }


    private String displayBook(Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //display menu
        this.setMenu(model, request);

        List<Publisher> publishers = publisherService.list();


        Book book = null;

        //if request.attribute contains id then get this bookContainer from bookService
        if(id != null && id > 0L) {//edit mode
            //get from DB
            book = bookService.getById(id);
            if(book == null) {
                return "redirect:/admin/books";
            }

        } else {
            book = new Book();

        }

        model.addAttribute("book", book);
        model.addAttribute("publishers", publishers);
        return "admin-book";
    }

    @RequestMapping(value="/admin/book/save", method=RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {

        //display menu
        setMenu(model,request);

        List<Publisher> publishers = publisherService.list();

        model.addAttribute("publishers", publishers);


        if (result.hasErrors()) {
            return "admin-book";
        }

        Book newBook = new Book();

        if(book.getId()!=null && book.getId().longValue()>0) {

            //get actual book
            newBook = bookService.getById(book.getId());
            if(newBook == null) {
                return "redirect:/admin/books";
            }
        }

        //copy properties
        newBook.setIsbn(book.getIsbn());
        newBook.setAvailable(book.isAvailable());
        newBook.setPublisher(book.getPublisher());
        newBook.setBookQuantity(book.getBookQuantity());

        bookService.save(newBook);
        model.addAttribute("book",newBook);
        model.addAttribute("success","success");

        return "admin-book";
    }
}
