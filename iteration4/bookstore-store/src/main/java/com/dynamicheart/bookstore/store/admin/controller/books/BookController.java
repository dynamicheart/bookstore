package com.dynamicheart.bookstore.store.admin.controller.books;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.publisher.PublisherService;
import com.dynamicheart.bookstore.core.utils.BookAvailabilityUtils;
import com.dynamicheart.bookstore.store.admin.model.catalog.book.BookContainer;
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

import static com.dynamicheart.bookstore.core.constants.CoreConstants.ALL_REGIONS;

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
    private BookAvailabilityUtils bookAvailabilityUtils;

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

        BookContainer bookContainer = new BookContainer();
        List<BookDescription> displayDescriptions = new ArrayList<BookDescription>();

        //if request.attribute contains id then get this bookContainer from bookService
        if(id != null && id > 0L) {//edit mode
            //get from DB
            Book dbBook = bookService.getById(id);
            if(dbBook == null) {
                return "redirect:/admin/books";
            }
            
            bookContainer.setBook(dbBook);

            Set<BookDescription> bookDescriptions = dbBook.getDescriptions();
            displayDescriptions.addAll(bookDescriptions);

            BookAvailability bookAvailability = bookAvailabilityUtils.getBookAvailability(dbBook);

            BigDecimal bookPrice = null;
            if(bookAvailability != null) {
                if(bookAvailability.getBookPrice() != null){
                    bookPrice = bookAvailability.getBookPrice();
                    bookContainer.setDisplayPrice(bookAvailabilityUtils.getAdminFormatedAmount(bookPrice));
                }
            }else {
                bookAvailability = new BookAvailability();
            }

            bookContainer.setBookAvailability(bookAvailability);
            bookContainer.setDescriptions(displayDescriptions);
        } else {
            BookDescription bookDescription = new BookDescription();
            displayDescriptions.add(bookDescription);

            Book book = new Book();
            book.setAvailable(true);

            BookAvailability bookAvailability = new BookAvailability();

            bookContainer.setBookAvailability(bookAvailability);
            bookContainer.setBook(book);
            bookContainer.setDescriptions(displayDescriptions);
        }

        model.addAttribute("bookContainer", bookContainer);
        model.addAttribute("publishers", publishers);
        return "admin-book";
    }

    @RequestMapping(value="/admin/book/save", method=RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("bookContainer") BookContainer bookContainer, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {

        //display menu
        setMenu(model,request);

        List<Publisher> publishers = publisherService.list();
        model.addAttribute("publishers", publishers);

        //validate price
        BigDecimal submitedPrice = null;
        try {
            submitedPrice = bookAvailabilityUtils.getAmountFromFormatString(bookContainer.getDisplayPrice());
        } catch (Exception e) {
            ObjectError error = new ObjectError("bookPrice",messages.getMessage("NotEmpty.bookContainer.bookPrice", locale));
            result.addError(error);
        }

        if (result.hasErrors()) {
            return "admin-book";
        }

        Book newBook = bookContainer.getBook();
        BookAvailability newBookAvailability = null;
        BigDecimal newBookPrice = null;

        Set<BookAvailability> availabilities = new HashSet<BookAvailability>();

        if(bookContainer.getBook().getId()!=null && bookContainer.getBook().getId().longValue()>0) {

            //get actual book
            newBook = bookService.getById(bookContainer.getBook().getId());
            if(newBook == null) {
                return "redirect:/admin/books";
            }

            //copy properties
            newBook.setIsbn(bookContainer.getBook().getIsbn());
            newBook.setAvailable(bookContainer.getBook().isAvailable());
            newBook.setPublisher(bookContainer.getBook().getPublisher());

            Set<BookAvailability> oldAvails = newBook.getAvailabilities();
            if(oldAvails != null && oldAvails.size() > 0){
                for(BookAvailability oldAvailability : oldAvails){
                    if(oldAvailability.getRegion().equals(ALL_REGIONS)){
                        newBookAvailability = oldAvailability;

                        newBookPrice = oldAvailability.getBookPrice();
                    }else{
                        availabilities.add(oldAvailability);
                    }

                }
            }
        }

        if(newBookAvailability==null) {
            newBookAvailability = new BookAvailability();
        }


        newBookAvailability.setBookQuantity(bookContainer.getBookAvailability().getBookQuantity());
        newBookAvailability.setBook(newBook);
        availabilities.add(newBookAvailability);

        newBook.setAvailabilities(availabilities);

        Set<BookDescription> descriptions = new HashSet<BookDescription>();
        if(bookContainer.getDescriptions()!=null && bookContainer.getDescriptions().size()>0) {

            for(BookDescription description : bookContainer.getDescriptions()) {
                description.setBook(newBook);
                descriptions.add(description);
            }
        }
        newBook.setDescriptions(descriptions);

        bookService.save(newBook);
        model.addAttribute("success","success");

        return "admin-book";
    }
}
