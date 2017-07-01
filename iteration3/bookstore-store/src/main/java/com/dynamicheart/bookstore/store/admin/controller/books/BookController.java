package com.dynamicheart.bookstore.store.admin.controller.books;

import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.price.BookPrice;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.publisher.PublisherService;
import com.dynamicheart.bookstore.core.utils.BookAvailabilityUtils;
import com.dynamicheart.bookstore.store.admin.model.catalog.book.Book;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import com.dynamicheart.bookstore.store.utils.DateUtil;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        com.dynamicheart.bookstore.core.model.catalog.book.Book dbBook = bookService.getByCode(isbn);

        long bookId = -1;//non existent
        if(dbBook!=null) {
            bookId = dbBook.getId();
        }

        return displayBook(bookId, model,request,response);
    }

    /**
     * List of books
     * @param model
     * @return
     * @throws Exception
     */
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
        List<BookDescription> descriptions = new ArrayList<BookDescription>();


        Book book = new Book();

        //if request.attribute contains id then get this book from bookService
        if(id!=null && id!=0) {//edit mode
            //get from DB
            com.dynamicheart.bookstore.core.model.catalog.book.Book dbBook = bookService.getById(id);
            if(dbBook==null) {
                return "redirect:/admin/books";
            }
            
            book.setBook(dbBook);
            Set<BookDescription> bookDescriptions = dbBook.getDescriptions();
            
            descriptions.add(bookDescriptions.iterator().next());

            BookAvailability bookAvailability = bookAvailabilityUtils.getBookAvailability(dbBook);

            BigDecimal bookPrice = null;

            if(bookAvailability != null) {
                if(bookAvailability.getBookPrice() != null){
                    bookPrice = bookAvailability.getBookPrice();
                    book.setBookPrice(bookAvailabilityUtils.getAdminFormatedAmount(bookPrice));
                }
            }else {
                bookAvailability = new BookAvailability();
            }


            book.setBookAvailability(bookAvailability);
            book.setPrice(bookPrice);
            book.setDescriptions(descriptions);

        } else {
            BookDescription bookDescription = new BookDescription();
            descriptions.add(bookDescription);

            com.dynamicheart.bookstore.core.model.catalog.book.Book bo = new com.dynamicheart.bookstore.core.model.catalog.book.Book();
            bo.setAvailable(true);

            BookAvailability bookAvailability = new BookAvailability();

            book.setBookAvailability(bookAvailability);
            book.setBook(bo);
            book.setDescriptions(descriptions);
        }

        model.addAttribute("book", book);
        model.addAttribute("publishers", publishers);
        return "admin-book";
    }


    @RequestMapping(value="/admin/book/save", method=RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("book") com.dynamicheart.bookstore.store.admin.model.catalog.book.Book  book, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {


        //display menu
        setMenu(model,request);


        List<Publisher> publishers = publisherService.list();


        model.addAttribute("publishers", publishers);

        //validate price
        BigDecimal submitedPrice = null;
        try {
            submitedPrice = new BigDecimal(book.getBookPrice());
        } catch (Exception e) {
            ObjectError error = new ObjectError("bookPrice",messages.getMessage("NotEmpty.book.bookPrice", locale));
            result.addError(error);
        }
        Date date = new Date();
        if(!StringUtils.isBlank(book.getDateAvailable())) {
            try {
                date = DateUtil.getDate(book.getDateAvailable());
                book.getBookAvailability().setBookDateAvailable(date);
                book.setDateAvailable(DateUtil.formatDate(date));
            } catch (Exception e) {
                ObjectError error = new ObjectError("dateAvailable",messages.getMessage("message.invalid.date", locale));
                result.addError(error);
            }
        }


        if (result.hasErrors()) {
            return "admin-book";
        }

        com.dynamicheart.bookstore.core.model.catalog.book.Book newBook = book.getBook();
        BookAvailability newBookAvailability = null;
        BookPrice newBookPrice = null;

        Set<BookPriceDescription> bookPriceDescriptions = null;

        //get tax class
        //TaxClass taxClass = newBook.getTaxClass();
        //TaxClass dbTaxClass = taxClassService.getById(taxClass.getId());
        Set<BookPrice> prices = new HashSet<BookPrice>();
        Set<BookAvailability> availabilities = new HashSet<BookAvailability>();

        if(book.getBook().getId()!=null && book.getBook().getId().longValue()>0) {


            //get actual book
            newBook = bookService.getById(book.getBook().getId());
            if(newBook == null) {
                return "redirect:/admin/books";
            }

            //copy properties
            newBook.setIsbn(book.getBook().getIsbn());
            newBook.setAvailable(book.getBook().isAvailable());
            newBook.setDateAvailable(date);
            newBook.setPublisher(book.getBook().getPublisher());

        }


        if(newBookPrice==null) {
            newBookPrice = new BookPrice();
            newBookPrice.setDefaultPrice(true);
            newBookPrice.setBookPriceAmount(submitedPrice);
        }


        if(newBookAvailability==null) {
            newBookAvailability = new BookAvailability();
        }


        newBookAvailability.setBookQuantity(book.getBookAvailability().getBookQuantity());
        newBookAvailability.setBook(newBook);
        newBookAvailability.setPrices(prices);
        availabilities.add(newBookAvailability);

        prices.add(newBookPrice);

        newBook.setAvailabilities(availabilities);

        Set<BookDescription> descriptions = new HashSet<BookDescription>();
        if(book.getDescriptions()!=null && book.getDescriptions().size()>0) {

            for(BookDescription description : book.getDescriptions()) {
                description.setBook(newBook);
                descriptions.add(description);

            }
        }
        newBook.setDescriptions(descriptions);
        book.setDateAvailable(DateUtil.formatDate(date));

        bookService.create(newBook);
        model.addAttribute("success","success");

        return "admin-book";
    }
}
