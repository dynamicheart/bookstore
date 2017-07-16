package com.dynamicheart.bookstore.test.core.util;

import com.dynamicheart.bookstore.core.utils.PriceUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dynamicheart on 7/2/2017.
 */

@Ignore
public class PriceUtilsTest {

    private PriceUtils priceUtils = new PriceUtils();

    @Test
    public void testFormat(){

        try {
            System.out.print(priceUtils.getAmountFromFormatString("123,123.01").toString());
        }catch (Exception e){
            System.out.print("fuck");
        }
    }
}
