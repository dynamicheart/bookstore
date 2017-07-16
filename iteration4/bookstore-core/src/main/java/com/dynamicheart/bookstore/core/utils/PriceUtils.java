package com.dynamicheart.bookstore.core.utils;


import com.dynamicheart.bookstore.core.constants.CoreConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Component("PriceUtil")
public class PriceUtils {

    private final static char DECIMALCOUNT = '2';
    private final static String DECIMALPOINT = ".";
    private final static String THOUSANDPOINT = ",";


    private static final Logger LOGGER = LoggerFactory.getLogger(PriceUtils.class);

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
        nf = NumberFormat.getInstance(CoreConstants.DEFAULT_LOCALE);

        nf.setMaximumFractionDigits(Integer.parseInt(Character
                .toString(DECIMALCOUNT)));
        nf.setMinimumFractionDigits(Integer.parseInt(Character
                .toString(DECIMALCOUNT)));
        return nf.format(amount);
    }
}
