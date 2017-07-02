package com.dynamicheart.bookstore.store.admin.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface WebUserServices extends UserDetailsService {
	
	void createDefaultAdmin() throws Exception;

}
