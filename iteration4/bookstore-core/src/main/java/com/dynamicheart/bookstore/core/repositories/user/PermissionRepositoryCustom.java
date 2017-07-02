package com.dynamicheart.bookstore.core.repositories.user;


import com.dynamicheart.bookstore.core.model.user.PermissionCriteria;
import com.dynamicheart.bookstore.core.model.user.PermissionList;

public interface PermissionRepositoryCustom {

	PermissionList listByCriteria(PermissionCriteria criteria);


}
