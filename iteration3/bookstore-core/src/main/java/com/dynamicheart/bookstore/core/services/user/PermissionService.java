package com.dynamicheart.bookstore.core.services.user;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.Permission;
import com.dynamicheart.bookstore.core.model.user.PermissionCriteria;
import com.dynamicheart.bookstore.core.model.user.PermissionList;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;


public interface PermissionService extends BookstoreEntityService<Integer, Permission> {

	List<Permission> getByName();

	List<Permission> listPermission()  throws ServiceException;

	Permission getById(Integer permissionId);

	List<Permission> getPermissions(List<Integer> groupIds) throws ServiceException;

	void deletePermission(Permission permission) throws ServiceException;

	PermissionList listByCriteria(PermissionCriteria criteria) throws ServiceException ;

	void removePermission(Permission permission, Group group) throws ServiceException;

}
