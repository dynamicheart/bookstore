package com.dynamicheart.bookstore.test.core.util;

import com.dynamicheart.bookstore.core.utils.BookAvailabilityUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dynamicheart on 7/2/2017.
 */

@Ignore
public class BookAvailabilityUtilsTest {

    private BookAvailabilityUtils bookAvailabilityUtils = new BookAvailabilityUtils();

    @Test
    public void testFormat(){

        try {
            System.out.print(bookAvailabilityUtils.getAmountFromFormatString("123,123.01").toString());
        }catch (Exception e){
            System.out.print("fuck");
        }
    }
}
