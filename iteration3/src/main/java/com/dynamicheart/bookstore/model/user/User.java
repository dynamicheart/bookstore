package com.dynamicheart.bookstore.model.user;


import com.dynamicheart.bookstore.constants.SchemaConstant;
import com.dynamicheart.bookstore.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.model.common.audit.Auditable;
import com.dynamicheart.bookstore.model.generic.BookstoreEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "USER", schema= SchemaConstant.BOOKSTORE_SHECMA)
public class User extends BookstoreEntity<Long, User> implements Auditable {
	
	
	private static final long serialVersionUID = 5401059537544058710L;
	
	@Id
	@Column(name = "USER_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USER_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	public User() {
		
	}
	
	public User(String userName, String password, String email) {
		
		this.adminName = userName;
		this.adminPassword = password;
		this.adminEmail = email;
	}
	
	@NotEmpty
	@Column(name="ADMIN_NAME", length=100, unique=true)
	private String adminName;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade = {CascadeType.REFRESH})
	@JoinTable(name = "USER_GROUP", schema=SchemaConstant.BOOKSTORE_SHECMA, joinColumns = {
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "GROUP_ID",
					nullable = false, updatable = false) }
	)
	@Cascade({
		org.hibernate.annotations.CascadeType.DETACH,
		org.hibernate.annotations.CascadeType.LOCK,
		org.hibernate.annotations.CascadeType.REFRESH,
		org.hibernate.annotations.CascadeType.REPLICATE
		
	})
	private List<Group> groups = new ArrayList<Group>();
	
	@NotEmpty
	@Email
	@Column(name="ADMIN_EMAIL")
	private String adminEmail;
	
	@NotEmpty
	@Column(name="ADMIN_PASSWORD", length=60)
	private String adminPassword;
	
	
	@Column(name="ADMIN_FIRST_NAME")
	private String firstName;
	
	@Column(name="ACTIVE")
	private boolean active = true;
	
	
	@Column(name="ADMIN_LAST_NAME")
	private String lastName;
	
	
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_ACCESS")
	private Date lastAccess;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_ACCESS")
	private Date loginTime;

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
	public void setAuditSection(AuditSection audit) {
		auditSection = audit;
		
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

}
