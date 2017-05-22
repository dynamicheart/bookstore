package com.dynamicheart.bookstore.model.user;


import com.dynamicheart.bookstore.constants.SchemaConstant;
import com.dynamicheart.bookstore.model.common.audit.AuditListener;
import com.dynamicheart.bookstore.model.common.audit.AuditSection;
import com.dynamicheart.bookstore.model.common.audit.Auditable;
import com.dynamicheart.bookstore.model.generic.BookstoreEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SM_GROUP", schema= SchemaConstant.BOOKSTORE_SHECMA)
public class Group extends BookstoreEntity<Integer, Group> implements Auditable {

	private static final long serialVersionUID = -5501508252681691905L;

	@Id
	@Column(name = "GROUP_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GROUP_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Integer id;
	
	public Group() {
		
	}
	
	@Column(name ="GROUP_TYPE")
	@Enumerated(value = EnumType.STRING)
	private GroupType groupType;
	
	@NotEmpty
	@Column(name="GROUP_NAME", unique=true)
	private String groupName;
	
	public Group(String groupName) {
		this.groupName = groupName;
	}
	
	@ManyToMany(mappedBy = "groups")
	private Set<Permission> permissions = new HashSet<Permission>();	
	
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	
	@Override
	public AuditSection getAuditSection() {
		return this.auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
			this.auditSection = audit;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	public GroupType getGroupType() {
		return groupType;
	}



}
