package com.dynamicheart.bookstore.store.admin.controller.books;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.price.BookPrice;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.publisher.PublisherService;
import com.dynamicheart.bookstore.core.utils.BookPriceUtils;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import com.dynamicheart.bookstore.store.utils.DateUtil;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private BookPriceUtils priceUtil;

    @RequestMapping(value="/admin/book/detail", method= RequestMethod.GET)
    public String displayBookEdit(@RequestParam("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayBook(id, model, request, response);
    }

    @RequestMapping(value="/admin/books/viewEditBook.html", method=RequestMethod.GET)
    public String displayBookEdit(@RequestParam("isbn") String isbn, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Book dbBook = bookService.getByCode(isbn);

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


        com.dynamicheart.bookstore.store.admin.model.catalog.book.Book book = new com.dynamicheart.bookstore.store.admin.model.catalog.book.Book();

        //if request.attribute contains id then get this book from bookService
        if(id!=null && id!=0) {//edit mode

            //get from DB
            Book dbBook = bookService.getById(id);
            if(dbBook==null) {
                return "redirect:/admin/books";
            }
            
            book.setBook(dbBook);
            Set<BookDescription> bookDescriptions = dbBook.getDescriptions();
            
            descriptions.add(bookDescriptions.iterator().next());

            BookAvailability bookAvailability = null;
            BookPrice bookPrice = null;

            Set<BookAvailability> availabilities = dbBook.getAvailabilities();
            if(availabilities != null && availabilities.size() > 0){
                bookAvailability = availabilities.iterator().next();
                Set<BookPrice> prices = bookAvailability.getPrices();
                if(prices != null && prices.size() > 0){
                    bookPrice = prices.iterator().next();
                    book.setBookPrice(priceUtil.getAdminFormatedAmount(bookPrice.getBookPriceAmount()));
                }
            }

            if(bookAvailability == null) {
                bookAvailability = new BookAvailability();
            }

            if(bookPrice==null) {
                bookPrice = new BookPrice();
            }

            book.setBookAvailability(bookAvailability);
            book.setPrice(bookPrice);
            book.setDescriptions(descriptions);


            book.setDateAvailable(DateUtil.formatDate(dbBook.getDateAvailable()));
            

        } else {
            BookDescription bookDescription = new BookDescription();
            descriptions.add(bookDescription);

            Book bo = new Book();
            bo.setAvailable(true);

            BookAvailability bookAvailability = new BookAvailability();
            BookPrice bookPrice = new BookPrice();

            book.setBookAvailability(bookAvailability);
            book.setPrice(bookPrice);
            book.setBook(bo);
            book.setDescriptions(descriptions);
            book.setDateAvailable(DateUtil.formatDate(new Date()));
        }

        model.addAttribute("book",book);
        model.addAttribute("publishers", publishers);
        return "admin-book";
    }
}
