package com.dynamicheart.bookstore.test.store.application;

import com.dynamicheart.bookstore.core.configuration.CoreApplicationConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by dynamicheart on 5/27/2017.
 */

@Configuration
@ComponentScan({"com.dynamicheart.bookstore.store","com.dynamicheart.bookstore.core"})
@EnableAutoConfiguration
@Import(CoreApplicationConfiguration.class)
@ImportResource("classpath:/spring/bookstore-store-context.xml")
@EnableWebSecurity
public class StoreApplicationConfigurationTest {
}
