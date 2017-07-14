package com.dynamicheart.bookstore.store.store.populator.customer;

import com.dynamicheart.bookstore.core.utils.exception.ConversionException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.utils.AbstractDataPopulator;
import com.dynamicheart.bookstore.store.store.model.customer.CustomerEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class CustomerEntityPopulator extends AbstractDataPopulator<Customer,CustomerEntity> {


    @Override
    public CustomerEntity populate(final Customer source, final CustomerEntity target, final Language language)
            throws ConversionException {
        try {
            target.setId(source.getId());
            if (StringUtils.isNotBlank(source.getEmailAddress())) {
                target.setEmailAddress(source.getEmailAddress());
            }


        } catch (Exception e) {
            throw new ConversionException(e);
        }

        return target;
    }

    @Override
    protected CustomerEntity createTarget() {
        return new CustomerEntity();
    }


}
