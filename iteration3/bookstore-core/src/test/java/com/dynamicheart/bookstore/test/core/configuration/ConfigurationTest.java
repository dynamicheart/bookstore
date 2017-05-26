package com.dynamicheart.bookstore.test.core.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "com.dynamicheart.bookstore.core.repositories")
@ComponentScan({"com.dynamicheart.bookstore.core.services","com.dynamicheart.bookstore.core.utils"})
@ImportResource("spring/test-bookstore-context.xml")
public class ConfigurationTest {

}
