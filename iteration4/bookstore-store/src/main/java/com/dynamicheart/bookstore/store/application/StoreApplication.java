package com.dynamicheart.bookstore.store.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by dynamicheart on 5/24/2017.
 */

@SpringBootApplication
public class StoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}
