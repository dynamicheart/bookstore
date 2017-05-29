package com.dynamicheart.bookstore.store.init.data;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by dynamicheart on 5/28/2017.
 */

@Component
public class InitializationLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationLoader.class);

    @Inject
    private InitData initData;

    @PostConstruct
    public void init(){
        try {
            LOGGER.info("Bookstore database is empty, populate it....");
            loadData();
        }catch (Exception e){
            LOGGER.error("Error in the init method",e);
        }
    }


    private void loadData() throws ServiceException{
        initData.initInitialData();
    }
}
