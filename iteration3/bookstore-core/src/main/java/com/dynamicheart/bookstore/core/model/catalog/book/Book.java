package com.dynamicheart.bookstore.core.model.catalog.book;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.core.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.core.model.common.audit.Auditable;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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

    @Column(name="DATE_AVAILABLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAvailable = new Date();

    @Column(name="AVAILABLE")
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name="PUBLISHER_ID", nullable=true)
    private Publisher publisher;

    @Column(name = "REVIEW_AVG")
    private BigDecimal bookReviewAvg;

    @Column(name = "REVIEW_COUNT")
    private Integer bookReviewCount;

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

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
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

    public BigDecimal getBookReviewAvg() {
        return bookReviewAvg;
    }

    public void setBookReviewAvg(BigDecimal bookReviewAvg) {
        this.bookReviewAvg = bookReviewAvg;
    }

    public Integer getBookReviewCount() {
        return bookReviewCount;
    }

    public void setBookReviewCount(Integer bookReviewCount) {
        this.bookReviewCount = bookReviewCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
