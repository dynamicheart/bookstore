package com.dynamicheart.bookstore.test.store.application;

import com.dynamicheart.bookstore.store.application.StoreApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dynamicheart on 5/27/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StoreApplicationConfiguration.class})
public class ApplicationTest {

    @Test
    public void contextLoad() throws Exception{

    }
}
