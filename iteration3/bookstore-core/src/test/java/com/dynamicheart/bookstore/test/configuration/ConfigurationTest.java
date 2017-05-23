package com.dynamicheart.bookstore.test.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.dynamicheart.bookstore.core.services","com.dynamicheart.bookstore.core.utils"})
@ImportResource("spring/test-bookstore-context.xml")
public class ConfigurationTest {

}
