package com.dynamicheart.bookstore.core.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.dynamicheart.bookstore.core.model"})
@ComponentScan({"com.dynamicheart.bookstore.core.services", "com.dynamicheart.bookstore.core.utils"})
@ImportResource("classpath:/spring/bookstore-core-context.xml")
public class CoreApplicationConfiguration {

}
