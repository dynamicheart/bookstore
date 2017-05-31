package com.dynamicheart.bookstore.core.utils;


import com.dynamicheart.bookstore.core.constants.Constants;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class determines the price that is displayed in the catalogue for a given item. 
 * It does not calculate the total price for a given item
 * @author casams1
 *
 */
@Component("priceUtil")
public class BookPriceUtils {
	
	private final static char DECIMALCOUNT = '2';
	private final static char DECIMALPOINT = '.';
	private final static char THOUSANDPOINT = ',';
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookPriceUtils.class);

    /**
     * This is the format that will be displayed
     * in the admin input text fields when editing
     * an entity having a BigDecimal to be displayed
     * as a raw amount 1,299.99
     * The admin user will also be force to input
     * the amount using that format
     * @param store
     * @param amount
     * @return
     * @throws Exception
     */
    public String getAdminFormatedAmount(BigDecimal amount) throws Exception {

        if(amount==null) {
            return "";
        }

        NumberFormat nf = null;


        nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);

        nf.setMaximumFractionDigits(Integer.parseInt(Character
                .toString(DECIMALCOUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character
                .toString(DECIMALCOUNT)));

        return nf.format(amount);
    }

}
