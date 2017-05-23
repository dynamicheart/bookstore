/**
 * This application is maintained by CSTI consulting (www.csticonsulting.com).
 * Licensed under LGPL - Feel free to use it and modify it to your needs !
 *
 */
package com.dynamicheart.bookstore.test.common;

import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.test.configuration.ConfigurationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;


/**
 * @author c.samson
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigurationTest.class)
public class AbstractBookstoreCoreTestCase {
	
	@Inject
	protected CustomerService customerService;

}
