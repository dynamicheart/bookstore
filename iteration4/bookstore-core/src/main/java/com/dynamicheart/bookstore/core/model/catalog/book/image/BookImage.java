package com.dynamicheart.bookstore.core.model.catalog.book.image;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;

import javax.persistence.*;

/**
 * Created by dynamicheart on 7/5/2017.
 */

@Entity
@Table(name = "BOOK_IMAGE", schema= SchemaConstant.BOOKSTORE_SCHEMA)
public class BookImage extends BookstoreEntity<Long, BookImage> {

    private static final long serialVersionUID = 2776268357880333124L;

    @Id
    @Column(name = "BOOK_IMAGE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "BOOK_IMG_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "DEFAULT_IMAGE")
    private boolean defaultImage = true;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
