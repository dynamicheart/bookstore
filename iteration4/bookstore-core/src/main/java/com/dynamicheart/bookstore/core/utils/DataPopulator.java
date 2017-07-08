package com.dynamicheart.bookstore.core.utils;

import com.dynamicheart.bookstore.core.exception.ConversionException;
import com.dynamicheart.bookstore.core.model.reference.language.Language;

/**
 * Created by dynamicheart on 7/7/2017.
 */
public interface DataPopulator<Source, Target> {
    public Target populate(Source source,Target target, Language language) throws ConversionException;
    public Target populate(Source source,Language language) throws ConversionException;
}
