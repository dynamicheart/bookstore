package com.dynamicheart.bookstore.core.utils;


import com.dynamicheart.bookstore.core.constants.Constants;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Component("availabilityUtil")
public class BookAvailabilityUtils {

    private final static char DECIMALCOUNT = '2';
    private final static String DECIMALPOINT = ".";
    private final static String THOUSANDPOINT = ",";


    private static final Logger LOGGER = LoggerFactory.getLogger(BookAvailabilityUtils.class);

    public BigDecimal getAmountFromFormatString(String amount) throws Exception{
        if(amount == null){
            return new BigDecimal(0);
        }

        return new BigDecimal(amount.replaceAll(THOUSANDPOINT, ""));
    }

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

    public BigDecimal getPrice(Book book) {
        BigDecimal price = calculatePrice(book);

        return price;
    }

    private BigDecimal calculatePrice(Book book) {
        BigDecimal price = null;

        Set<BookAvailability> availabilities = book.getAvailabilities();
        for(BookAvailability availability : availabilities) {
            if(availability.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1 accept a region
                price = availability.getBookPrice();
            }
        }

        return price;
    }

    public BookAvailability getBookAvailability(Book book) {
        BookAvailability bookAvailability =null;

        Set<BookAvailability> availabilities = book.getAvailabilities();
        for(BookAvailability avail : availabilities) {
            if(avail.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1 accept a region
                bookAvailability = avail;
            }
        }
        return bookAvailability;
    }


    public String getFormatedAmountWithCurrency(Locale locale, com.dynamicheart.bookstore.core.model.reference.currency.Currency currency, BigDecimal amount) throws Exception {
        if(amount==null) {
            return "";
        }

        Currency curr = currency.getCurrency();

        NumberFormat currencyInstance = null;

        currencyInstance = NumberFormat.getCurrencyInstance(locale);
        currencyInstance.setCurrency(curr);
        return currencyInstance.format(amount.doubleValue());


    }

    public String getFormatedAmountWithCurrency(com.dynamicheart.bookstore.core.model.reference.currency.Currency currency, BigDecimal amount) throws Exception {
        if(amount==null) {
            return "";
        }

        Validate.notNull(currency.getCurrency(),"Currency must be populated with java.util.Currency");

        NumberFormat nf = null;


        Currency curr = currency.getCurrency();
        nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);
        nf.setMaximumFractionDigits(Integer.parseInt(Character
                .toString(DECIMALCOUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character
                .toString(DECIMALCOUNT)));
        nf.setCurrency(curr);


        String stringNumber = nf.format(amount);

        return stringNumber;
    }

}
