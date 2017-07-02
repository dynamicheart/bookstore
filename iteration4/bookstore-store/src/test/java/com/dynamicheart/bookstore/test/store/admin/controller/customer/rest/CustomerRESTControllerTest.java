package com.dynamicheart.bookstore.test.store.admin.controller.customer.rest;

import com.dynamicheart.bookstore.test.store.common.AbstractBookstoreStoreTestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by dynamicheart on 5/27/2017.
 */

@Ignore
public class CustomerRESTControllerTest extends AbstractBookstoreStoreTestCase{

    private static final String ENDPOINT = "http://localhost:8080/admin/api/customers";

    @Test
    public void fetchPage(){
        given().params("page", "0", "size", "2").get(ENDPOINT)
                .then()
                .assertThat().body("content.name", hasItems("Bryan", "Ben"));
    }
}
