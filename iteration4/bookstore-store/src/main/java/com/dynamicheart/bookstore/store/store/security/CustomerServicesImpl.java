package com.dynamicheart.bookstore.store.store.security;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.Permission;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.core.services.user.PermissionService;
import com.dynamicheart.bookstore.store.admin.security.SecurityDataAccessException;
import com.dynamicheart.bookstore.store.common.constants.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("customerDetailsService")
public class CustomerServicesImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServicesImpl.class);
	
	public final static String ROLE_PREFIX = "ROLE_";//Spring Security 4
	
	@Inject
	private CustomerService customerService;
	

	
	@Inject
	protected PermissionService permissionService;
	
	@Inject
	protected GroupService groupService;
	
	
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		Customer user = null;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		try {

				user = customerService.getByNick(userName);
			
				if(user==null) {
					return null;
				}
	
	

			GrantedAuthority role = new SimpleGrantedAuthority(ROLE_PREFIX + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);//required to login
			authorities.add(role); 
			
			List<Integer> groupsId = new ArrayList<Integer>();
			List<Group> groups = user.getGroups();
			for(Group group : groups) {
				groupsId.add(group.getId());
			}
			
	
			if(CollectionUtils.isNotEmpty(groupsId)) {
		    	List<Permission> permissions = permissionService.getPermissions(groupsId);
		    	for(Permission permission : permissions) {
		    		GrantedAuthority auth = new SimpleGrantedAuthority(permission.getPermissionName());
		    		authorities.add(auth);
		    	}
			}
			

			

		
		
		} catch (ServiceException e) {
			LOGGER.error("Exception while querrying customer",e);
			throw new SecurityDataAccessException("Cannot authenticate customer",e);
		}
		
		User authUser = new User(userName, user.getPassword(), true, true,
				true, true, authorities);
		
		return authUser;
		
		
	}
	




}
