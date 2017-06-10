/**
 * 
 */
package com.dynamicheart.bookstore.core.model.shoppingcart;



import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.core.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.core.model.common.audit.Auditable;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SHOPPING_CART", schema= SchemaConstant.BOOKSTORE_SCHEMA, indexes= { @Index(name = "SHP_CART_CODE_IDX", columnList = "SHP_CART_CODE"), @Index(name = "SHP_CART_CUSTOMER_IDX", columnList = "CUSTOMER_ID")})
public class ShoppingCart extends BookstoreEntity<Long, ShoppingCart> implements Auditable {

	
	private static final long serialVersionUID = 1L;
	
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Id
	@Column(name = "SHP_CART_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHP_CRT_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@Column(name = "SHP_CART_CODE", unique=true, nullable=false)
	private String shoppingCartCode;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCart")
	private Set<ShoppingCartItem> lineItems = new HashSet<ShoppingCartItem>();
	
	@Column(name = "CUSTOMER_ID", nullable = true)
	private Long customerId;
	
	@Transient
	private boolean obsolete = false;//when all items are obsolete
    
	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		this.auditSection = audit;
		
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	public boolean isObsolete() {
		return obsolete;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

	public Set<ShoppingCartItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<ShoppingCartItem> lineItems) {
		this.lineItems = lineItems;
	}

    public String getShoppingCartCode()
    {
        return shoppingCartCode;
    }

    public void setShoppingCartCode( String shoppingCartCode )
    {
        this.shoppingCartCode = shoppingCartCode;
    }


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

}
