package com.dynamicheart.bookstore.core.constants;

import java.util.HashMap;

/**
 * Created by dynamicheart on 5/3/2017.
 */
public class SchemaConstant {

    public final static String BOOKSTORE_SCHEMA = "BOOKSTORE";

    public static final String[] LANGUAGE_ISO_CODE = {"en", "ch"};

    public static final String ALL_REGIONS = "*";

    public static final HashMap<String, String> CURRENCY_MAP = new HashMap<String, String>();

    static {
        CURRENCY_MAP.put("USD", "US Dollar");
        CURRENCY_MAP.put("CNY", "Yuan Renminbi");
    }
}
