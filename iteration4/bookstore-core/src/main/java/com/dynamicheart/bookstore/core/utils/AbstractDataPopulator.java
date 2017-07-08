package com.dynamicheart.bookstore.core.utils;

import com.dynamicheart.bookstore.core.exception.ConversionException;
import com.dynamicheart.bookstore.core.model.reference.language.Language;

import java.util.Locale;

/**
 * Created by dynamicheart on 7/7/2017.
 */
public abstract class AbstractDataPopulator<Source,Target> implements DataPopulator<Source, Target>{

    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    public Locale getLocale() {
        return locale;
    }


    @Override
    public Target populate(Source source,Language language) throws ConversionException {
        return populate(source,createTarget(), language);
    }

    protected abstract Target createTarget();

}
