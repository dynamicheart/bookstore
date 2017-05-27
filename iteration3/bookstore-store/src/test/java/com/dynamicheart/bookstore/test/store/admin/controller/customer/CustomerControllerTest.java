package com.dynamicheart.bookstore.test.store.admin.controller.customer;

import com.dynamicheart.bookstore.store.admin.controller.customers.CustomerController;
import com.dynamicheart.bookstore.test.store.common.AbstractBookstoreStoreTestCase;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dynamicheart on 5/27/2017.
 */

@Ignore
public class CustomerControllerTest extends AbstractBookstoreStoreTestCase {

    @Inject
    private CustomerController customerController;

    @Test
    public void contextLoad() throws Exception{
        assertThat(customerController).isNotNull();
    }

}
