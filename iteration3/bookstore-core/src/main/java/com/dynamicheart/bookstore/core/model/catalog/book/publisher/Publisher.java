package com.dynamicheart.bookstore.core.model.catalog.book.publisher;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.core.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dynamicheart on 5/3/2017.
 */

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PUBLISHER", schema=SchemaConstant.BOOKSTORE_SCHEMA, uniqueConstraints=
@UniqueConstraint(columnNames = {"CODE"}) )
public class Publisher extends BookstoreEntity<Long, Publisher> implements Serializable{

    private static final long serialVersionUID = 4262781652879631158L;

    @Id
    @Column(name = "PUBLISHER_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PUBLI_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<PublisherDescription> descriptions = new HashSet<PublisherDescription>();

    @Column(name="SORT_ORDER")
    private Integer order = new Integer(0);

    @NotEmpty
    @Column(name="CODE", length=100, nullable=false)
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditSection getAuditSection() {
        return auditSection;
    }

    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<PublisherDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<PublisherDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
