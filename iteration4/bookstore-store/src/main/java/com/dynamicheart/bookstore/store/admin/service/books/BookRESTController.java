package com.dynamicheart.bookstore.store.admin.service.books;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.dynamicheart.bookstore.store.utils.BriefUtils.getBookBriefs;

/**
 * Created by dynamicheart on 5/29/2017.
 */

@RestController
public class BookRESTController {
    
    @Inject
    private BookService bookService;

    @RequestMapping(
            value = "api/admin/books",
            params = {"draw", "start","length"},
            method = RequestMethod.GET
    )
    public @ResponseBody
    Map<String, Object> pageBooks(
            @RequestParam("draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length){
        Map<String, Object> result = new HashMap<>();
        Page<Book> pages = bookService.findPaginated(start/length, length);
        result.put("data", getBookBriefs(pages.getContent()));
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotalElements());
        result.put("recordsFiltered",pages.getTotalElements());
        return result;
    }

    @RequestMapping(
            value="api/admin/book/{id}",
            method=RequestMethod.DELETE
    )
    public @ResponseBody
    ResponseEntity<String> deleteBook(
            @PathVariable("id") Long id){
        try {
            Book book = bookService.getById(id);

            if(book == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            } else {
                bookService.delete(book);
                return new ResponseEntity<String>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
