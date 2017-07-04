package com.dynamicheart.bookstore.core.model.catalog.book.image;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;

import javax.persistence.*;
import java.io.InputStream;

/**
 * Created by dynamicheart on 6/2/2017.
 */

@Entity
@Table(name = "BOOK_IMAGE", schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class BookImage extends BookstoreEntity<Long, BookImage>{

    private static final long serialVersionUID = 2776268357880333124L;

    @Id
    @Column(name = "BOOK_IMAGE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "BOOK_IMG_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "BOOK_IMAGE")
    private String bookImage;

    @Column(name = "DEFAULT_IMAGE")
    private boolean defaultImage = true;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @Transient
    private InputStream image = null;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public boolean isDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
