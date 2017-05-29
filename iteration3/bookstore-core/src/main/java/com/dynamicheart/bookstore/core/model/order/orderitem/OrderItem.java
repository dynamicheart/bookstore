package com.dynamicheart.bookstore.core.model.order.orderitem;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import com.dynamicheart.bookstore.core.model.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by dynamicheart on 5/23/2017.
 */

@Entity
@Table(name = "ORDER_ITEM", schema = SchemaConstant.BOOKSTORE_SHECMA)
public class OrderItem extends BookstoreEntity<Long, OrderItem>{

    private static final long serialVersionUID = 6839636201840623165L;

    @Id
    @Column(name="ORDER_PRODUCT_ID")
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_ITEM_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "BOOK_ISBN", length = 13)
    private String isbn;

    @Column (name="BOOK_NAME" , length=64 , nullable=false)
    private String bookName;

    @Column (name="BOOK_QUANTITY")
    private int bookQuantity;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column (name="BOOK_PRICE" , nullable=false )
    private BigDecimal bookPrice;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }
}
