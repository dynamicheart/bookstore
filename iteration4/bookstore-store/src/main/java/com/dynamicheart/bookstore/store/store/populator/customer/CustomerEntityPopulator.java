package com.dynamicheart.bookstore.store.store.populator.customer;

import com.dynamicheart.bookstore.core.model.customer.avatar.CustomerAvatar;
import com.dynamicheart.bookstore.core.utils.exception.ConversionException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.utils.AbstractDataPopulator;
import com.dynamicheart.bookstore.store.store.model.ReadableImage;
import com.dynamicheart.bookstore.store.store.model.customer.CustomerEntity;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 7/8/2017.
 */

@Component
public class CustomerEntityPopulator extends AbstractDataPopulator<Customer,CustomerEntity> {

    @Inject
    ImageFilePath imageUtils;

    @Override
    public CustomerEntity populate(final Customer source, final CustomerEntity target, final Language language)
            throws ConversionException {
        try {
            target.setId(source.getId());
            if (StringUtils.isNotBlank(source.getEmailAddress())) {
                target.setEmailAddress(source.getEmailAddress());
            }
            target.setUserName(source.getNick());
            target.setGender(source.getGender());

            CustomerAvatar avatar = source.getDefaultAvatar();
            if(avatar != null){
                ReadableImage readableImage = new ReadableImage();
                readableImage.setResourceId(avatar.getResourceId());

                String contextPath = imageUtils.getContextPath();
                String basePath = imageUtils.getBasePath();
                StringBuilder imagePath = new StringBuilder();
                imagePath.append(contextPath).append(basePath).append(avatar.getResourceId());
                readableImage.setImageUrl(imagePath.toString());

                target.setImage(readableImage);
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
