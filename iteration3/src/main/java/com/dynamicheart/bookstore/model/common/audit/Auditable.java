package com.dynamicheart.bookstore.model.common.audit;

public interface Auditable {
	
	AuditSection getAuditSection();
	
	void setAuditSection(AuditSection audit);
}
