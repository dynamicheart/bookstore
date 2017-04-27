package com.dynamicheart.bookstore.api;

import com.dynamicheart.bookstore.domain.Book;
import com.dynamicheart.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dynamicheart on 4/25/2017.
 */

@RestController
@RequestMapping("/api")
public class BookRestController {
    @Autowired
    private BookService bookService;

    //Retrieve All Books
    @RequestMapping(value = "/book", method = GET)
    public ResponseEntity<List<Book>> listAllBooks(){
        List<Book> books = bookService.findBooks();
        if(books.isEmpty()){
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    //Retrieve Single Book
    @RequestMapping(value = "/book/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        Book book = bookService.findOne(id);
        if (book == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    //Create a Book
    @RequestMapping(value = "/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {

        if (bookService.isExist(book)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        bookService.save(book);

        System.out.println(book.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
        headers.set("bookID",Long.toString(book.getId()));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update a Book
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {

        if (bookService.findOne(id)==null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        bookService.update(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    //Delete a Book
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        Book book = bookService.findOne(id);
        if (book == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        bookService.delete(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    //Delete All Books
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteAllBooks() {
        //bookService.deleteAllBooks();
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
}
