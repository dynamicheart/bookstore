package com.dynamicheart.bookstore.test.core.book.image;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.test.core.common.AbstractBookstoreCoreTestCase;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dynamicheart on 6/7/2017.
 */

@Ignore
public class BookImageTest extends AbstractBookstoreCoreTestCase{

    @Test
    public void createBookImage() throws ServiceException{

        BookImage bookImage = new BookImage();
        bookImage.setBookImage("test.png");
        bookImage.setBookImageUrl("/book/test.png");
        bookImage.setDefaultImage(false);
        bookImage.setImageCrop(true);
        bookImage.setImageType(1);
        bookImage.setBookId(1L);
        bookImage.setImageContent(new byte[]{});
        bookImageService.save(bookImage);

        for(BookImage bookImage1: bookImageService.list()){
            System.out.println(bookImage1);
        }
    }
}
