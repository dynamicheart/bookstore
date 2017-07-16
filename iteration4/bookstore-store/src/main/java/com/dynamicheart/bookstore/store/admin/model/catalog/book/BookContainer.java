package com.dynamicheart.bookstore.store.admin.model.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynamicheart on 6/1/2017.
 */
public class BookContainer implements Serializable{

    private static final long serialVersionUID = 1619534688796132733L;

    @Valid
    private Book book;

    @Valid
    private List<BookDescription> descriptions = new ArrayList<BookDescription>();


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<BookDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<BookDescription> descriptions) {
        this.descriptions = descriptions;
    }

}
