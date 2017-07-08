package com.dynamicheart.bookstore.store.populator.catalog;

import com.dynamicheart.bookstore.core.exception.ConversionException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.PricingService;
import com.dynamicheart.bookstore.core.utils.AbstractDataPopulator;
import com.dynamicheart.bookstore.store.model.catalog.ReadableImage;
import com.dynamicheart.bookstore.store.model.catalog.book.AdaptableBookDescription;
import com.dynamicheart.bookstore.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.model.catalog.publisher.PublisherDescription;
import com.dynamicheart.bookstore.store.model.catalog.publisher.ReadablePublisher;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import org.apache.commons.lang3.Validate;

/**
 * Created by dynamicheart on 7/8/2017.
 */
public class ReadableBookPopulator extends AbstractDataPopulator<Book, ReadableBook> {

    private PricingService pricingService;

    private ImageFilePath imageUtils;

    public PricingService getPricingService() {
        return pricingService;
    }

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public ImageFilePath getImageUtils() {
        return imageUtils;
    }

    public void setImageUtils(ImageFilePath imageUtils) {
        this.imageUtils = imageUtils;
    }

    @Override
    public ReadableBook populate(Book source, ReadableBook target, Language language) throws ConversionException {
        Validate.notNull(pricingService, "Requires to set PricingService");
        Validate.notNull(imageUtils, "Requires to set imageUtils");

        try{
            target.setId(source.getId());
            target.setIsbn(source.getIsbn());


            BookDescription bookDescription = source.getBookDescription();
            if(bookDescription != null){
                AdaptableBookDescription targetDescription = new AdaptableBookDescription();
                targetDescription.setFriendlyUrl(bookDescription.getSeUrl());
                targetDescription.setName(bookDescription.getName());
                targetDescription.setDescription(bookDescription.getDescription());
                target.setDescription(targetDescription);
            }

            if(source.getPublisher() != null){
                ReadablePublisher publisher = new ReadablePublisher();
                PublisherDescription publisherDescription = new PublisherDescription();
                publisherDescription.setName(source.getPublisher().getDescriptions().iterator().next().getName());
                publisher.setDescription(publisherDescription);
                publisher.setId(source.getPublisher().getId());
                publisher.setOrder(source.getPublisher().getOrder());
                publisher.setCode(source.getPublisher().getCode());
                target.setPublisher(publisher);
            }

            BookImage image = source.getBookImage();
            if(image != null){
                ReadableImage readableImage = new ReadableImage();
                readableImage.setResourceId(image.getResourceId());

                String contextPath = imageUtils.getContextPath();
                String basePath = imageUtils.getBasePath();
                StringBuilder imagePath = new StringBuilder();
                imagePath.append(contextPath).append(basePath).append(image.getResourceId());
                readableImage.setImageUrl(imagePath.toString());

                target.setDefaultImage(readableImage);
            }

            target.setPrice(pricingService.calculateBookPrice(source));
            target.setDisplayPrice(pricingService.getStringAmount(target.getPrice()));

            return target;
        }catch (Exception e){
            throw new ConversionException(e);
        }

    }

    @Override
    protected ReadableBook createTarget() {
        return null;
    }
}
