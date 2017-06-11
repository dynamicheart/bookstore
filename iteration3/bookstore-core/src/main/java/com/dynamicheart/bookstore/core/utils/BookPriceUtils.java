package com.dynamicheart.bookstore.core.utils;


import com.dynamicheart.bookstore.core.constants.Constants;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.price.BookPrice;
import com.dynamicheart.bookstore.core.model.catalog.book.price.FinalPrice;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Component("priceUtil")
public class BookPriceUtils {

    private final static char DECIMALCOUNT = '2';
    private final static char DECIMALPOINT = '.';
    private final static char THOUSANDPOINT = ',';


    private static final Logger LOGGER = LoggerFactory.getLogger(BookPriceUtils.class);

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

    public FinalPrice getFinalPrice(Book book) {
        FinalPrice finalPrice = calculateFinalPrice(book);

        return finalPrice;
    }

    private FinalPrice calculateFinalPrice(Book book) {

        FinalPrice finalPrice = null;;
        List<FinalPrice> otherPrices = null;


        Set<BookAvailability> availabilities = book.getAvailabilities();
        for(BookAvailability availability : availabilities) {
            if(availability.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1 accept a region
                Set<BookPrice> prices = availability.getPrices();
                for(BookPrice price : prices) {

                    FinalPrice p = finalPrice(price);
                    if(price.isDefaultPrice()) {
                        finalPrice = p;
                    } else {
                        if(otherPrices==null) {
                            otherPrices = new ArrayList<FinalPrice>();
                        }
                        otherPrices.add(p);
                    }
                }
            }
        }


        if(finalPrice!=null) {
            finalPrice.setAdditionalPrices(otherPrices);
        } else {
            if(otherPrices!=null) {
                finalPrice = otherPrices.get(0);
            }
        }

        return finalPrice;
    }


    private FinalPrice finalPrice(BookPrice price) {

        FinalPrice finalPrice = new FinalPrice();
        BigDecimal fPrice = price.getBookPriceAmount();
        BigDecimal oPrice = price.getBookPriceAmount();

        Date today = new Date();
        //calculate discount price
        boolean hasDiscount = false;
        if(price.getBookPriceSpecialStartDate()!=null
                || price.getBookPriceSpecialEndDate()!=null) {


            if(price.getBookPriceSpecialStartDate()!=null) {
                if(price.getBookPriceSpecialStartDate().before(today)) {
                    if(price.getBookPriceSpecialEndDate()!=null) {
                        if(price.getBookPriceSpecialEndDate().after(today)) {
                            hasDiscount = true;
                            fPrice = price.getBookPriceSpecialAmount();
                            finalPrice.setDiscountEndDate(price.getBookPriceSpecialEndDate());
                        }
                    }

                }
            }


            if(!hasDiscount && price.getBookPriceSpecialStartDate()==null && price.getBookPriceSpecialEndDate()!=null) {
                if(price.getBookPriceSpecialEndDate().after(today)) {
                    hasDiscount = true;
                    fPrice = price.getBookPriceSpecialAmount();
                    finalPrice.setDiscountEndDate(price.getBookPriceSpecialEndDate());
                }
            }
        } else {
            if(price.getBookPriceSpecialAmount()!=null && price.getBookPriceSpecialAmount().doubleValue()>0) {
                hasDiscount = true;
                fPrice = price.getBookPriceSpecialAmount();
                finalPrice.setDiscountEndDate(price.getBookPriceSpecialEndDate());
            }
        }

        finalPrice.setBookPrice(price);
        finalPrice.setFinalPrice(fPrice);
        finalPrice.setOriginalPrice(oPrice);


        if(price.isDefaultPrice()) {
            finalPrice.setDefaultPrice(true);
        }
        if(hasDiscount) {
            discountPrice(finalPrice);
        }


        return finalPrice;
    }

    private void discountPrice(FinalPrice finalPrice) {

        finalPrice.setDiscounted(true);

        double arith = finalPrice.getBookPrice().getBookPriceSpecialAmount().doubleValue() / finalPrice.getBookPrice().getBookPriceAmount().doubleValue();
        double fsdiscount = 100 - (arith * 100);
        Float percentagediscount = new Float(fsdiscount);
        int percent = percentagediscount.intValue();
        finalPrice.setDiscountPercent(percent);

        //calculate percent
        BigDecimal price = finalPrice.getOriginalPrice();
        finalPrice.setDiscountedPrice(finalPrice.getBookPrice().getBookPriceSpecialAmount());
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
