package com.dynamicheart.bookstore.core.model.catalog.book.publisher;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dynamicheart on 5/3/2017.
 */

@Entity
@Table(name = "PUBLISHER", schema = SchemaConstant.BOOKSTORE_SHECMA)
public class Publisher implements Serializable{

    private static final long serialVersionUID = 4262781652879631158L;

    @Id
    @Column(name = "MANUFACTURER_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "MANUFACT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
