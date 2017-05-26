/**
 * This application is maintained by CSTI consulting (www.csticonsulting.com).
 * Licensed under LGPL - Feel free to use it and modify it to your needs !
 *
 */
package com.dynamicheart.bookstore.test.store.common;

import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.store.application.StoreApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;


/**
 * @author c.samson
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StoreApplicationConfiguration.class)
public class AbstractBookstoreStoreTestCase {
	
	@Inject
	protected CustomerService customerService;

}
