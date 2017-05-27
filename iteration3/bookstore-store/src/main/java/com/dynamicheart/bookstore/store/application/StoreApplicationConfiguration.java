package com.dynamicheart.bookstore.store.application;

import com.dynamicheart.bookstore.core.configuration.CoreApplicationConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by dynamicheart on 5/24/2017.
 */

@Configuration
@ComponentScan({"com.dynamicheart.bookstore.store","com.dynamicheart.bookstore.core"})
@EnableAutoConfiguration
@Import(CoreApplicationConfiguration.class)
@ImportResource("classpath:/spring/bookstore-store-context.xml")
@EnableWebSecurity
public class StoreApplicationConfiguration {
    /**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles-admin.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     */

    @Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }
}
