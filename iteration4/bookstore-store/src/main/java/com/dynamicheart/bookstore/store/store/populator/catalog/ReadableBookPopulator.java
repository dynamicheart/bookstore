package com.dynamicheart.bookstore.store.store.populator.catalog;

import com.dynamicheart.bookstore.core.utils.exception.ConversionException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.utils.AbstractDataPopulator;
import com.dynamicheart.bookstore.store.store.model.ReadableImage;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableAndPersistableBookDescription;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.store.model.catalog.publisher.PublisherDescription;
import com.dynamicheart.bookstore.store.store.model.catalog.publisher.ReadablePublisher;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 7/8/2017.
 */

@Component
public class ReadableBookPopulator extends AbstractDataPopulator<Book, ReadableBook> {

    @Inject
    private ImageFilePath imageUtils;

    @Override
    public ReadableBook populate(Book source, ReadableBook target, Language language) throws ConversionException {

        try{
            target.setId(source.getId());
            target.setIsbn(source.getIsbn());
            target.setQuantity(source.getBookQuantity());
            target.setPrice(source.getBookPrice());


            BookDescription bookDescription = source.getBookDescription();
            if(bookDescription != null){
                ReadableAndPersistableBookDescription targetDescription = new ReadableAndPersistableBookDescription();
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
