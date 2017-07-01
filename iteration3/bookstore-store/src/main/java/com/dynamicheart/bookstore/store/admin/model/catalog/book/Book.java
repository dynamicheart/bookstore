package com.dynamicheart.bookstore.store.admin.model.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
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
public class Book implements Serializable{

    private static final long serialVersionUID = 1619534688796132733L;

    @Valid
    private com.dynamicheart.bookstore.core.model.catalog.book.Book book;

    @Valid
    private List<BookDescription> descriptions = new ArrayList<BookDescription>();

    @Valid
    private BookAvailability bookAvailability;

    @Valid
    private BigDecimal price;

    @NotEmpty
    private String bookPrice = "0";

    private BookDescription description = null;

    public com.dynamicheart.bookstore.core.model.catalog.book.Book getBook() {
        return book;
    }

    public void setBook(com.dynamicheart.bookstore.core.model.catalog.book.Book book) {
        this.book = book;
    }

    public List<BookDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<BookDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public BookAvailability getBookAvailability() {
        return bookAvailability;
    }

    public void setBookAvailability(BookAvailability bookAvailability) {
        this.bookAvailability = bookAvailability;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public BookDescription getDescription() {
        return description;
    }

    public void setDescription(BookDescription description) {
        this.description = description;
    }
}
