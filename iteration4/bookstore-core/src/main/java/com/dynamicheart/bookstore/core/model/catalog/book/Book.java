package com.dynamicheart.bookstore.core.model.catalog.book;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import com.dynamicheart.bookstore.core.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.core.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.core.model.common.audit.Auditable;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dynamicheart on 5/3/2017.
 */

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "BOOK", schema=SchemaConstant.BOOKSTORE_SCHEMA, uniqueConstraints =
@UniqueConstraint(columnNames = {"ISBN"}))
public class Book extends BookstoreEntity<Long, Book> implements Auditable {

    private static final long serialVersionUID = -6879309779332486129L;

    @Id
    @Column(name = "BOOK_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "BOOK_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
    private Set<BookDescription> descriptions = new HashSet<BookDescription>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="book")
    private Set<BookAvailability> availabilities = new HashSet<BookAvailability>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "book")//cascade is set to remove because book save requires logic to create physical image first and then save the image id in the database, cannot be done in cascade
    private Set<BookImage> images = new HashSet<BookImage>();

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "BOOK_CATEGORY", schema=SchemaConstant.BOOKSTORE_SCHEMA, joinColumns = {
            @JoinColumn(name = "BOOK_ID", nullable = false, updatable = false) }
            ,
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID",
                    nullable = false, updatable = false) }
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private Set<Category> categories = new HashSet<Category>();

    @Column(name="AVAILABLE")
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name="PUBLISHER_ID", nullable=true)
    private Publisher publisher;

    @NotEmpty
    @Column(name = "ISBN", nullable = false, length = 13)
    private String isbn;

    public Book() {
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }

    public Set<BookDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<BookDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public Set<BookAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<BookAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    public Set<BookImage> getImages() {
        return images;
    }

    public void setImages(Set<BookImage> images) {
        this.images = images;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookDescription getBookDescription() {
        if(this.getDescriptions()!=null && this.getDescriptions().size()>0) {
            return this.getDescriptions().iterator().next();
        }
        return null;
    }

    public BookImage getBookImage() {
        BookImage bookImage = null;
        if(this.getImages()!=null && this.getImages().size()>0) {
            for(BookImage image : this.getImages()) {
                bookImage = image;
                if(bookImage.isDefaultImage()) {
                    break;
                }
            }
        }
        return bookImage;
    }
}
