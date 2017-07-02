package com.dynamicheart.bookstore.core.services.catalog.book.image;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImageSize;
import com.dynamicheart.bookstore.core.model.content.FileContentType;
import com.dynamicheart.bookstore.core.model.content.ImageContentFile;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
import com.dynamicheart.bookstore.core.modules.cms.book.BookFileManager;
import com.dynamicheart.bookstore.core.repositories.catalog.book.image.BookImageRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynamicheart on 6/6/2017.
 */

@Service("bookImage")
public class BookImageServiceImpl extends BookstoreEntityServiceImpl<Long, BookImage> implements BookImageService{

    private BookImageRepository bookImageRepository;

    @Inject
    private BookFileManager bookFileManager;

    @Inject
    public BookImageServiceImpl(BookImageRepository bookImageRepository) {
        super(bookImageRepository);
        this.bookImageRepository = bookImageRepository;
    }

    public BookImage getById(Long id) {
        
        return bookImageRepository.findOne(id);
    }

    @Override
    public void addBookImage(Book book, BookImage bookImage, ImageContentFile inputImage) throws ServiceException {
        bookImage.setBook(book);

        try {
            Assert.notNull(inputImage.getFile(),"ImageContentFile.file cannot be null");

            bookFileManager.addBookImage(bookImage, inputImage);

            //insert BookImage
            this.saveOrUpdate(bookImage);

        } catch (Exception e) {
            throw new ServiceException(e);
        } finally {
            try {
                if(inputImage.getFile()!=null) {
                    inputImage.getFile().close();
                }
            } catch(Exception ignore) {
            }
        }
    }

    @Override
    public OutputContentFile getBookImage(BookImage bookImage, BookImageSize size) throws ServiceException {

        BookImage bi = new BookImage();
        String imageName = bookImage.getBookImage();
        if(size == BookImageSize.LARGE) {
            imageName = "L-" + imageName;
        }

        if(size == BookImageSize.SMALL) {
            imageName = "S-" + imageName;
        }

        bi.setBookImage(imageName);
        bi.setBook(bookImage.getBook());

        OutputContentFile outputImage = bookFileManager.getBookImage(bi);

        return outputImage;
    }

    @Override
    public List<OutputContentFile> getBookImages(Book book) throws ServiceException {
        return bookFileManager.getImages(book);
    }

    @Override
    public void removeBookImage(BookImage bookImage) throws ServiceException {
        if(!StringUtils.isBlank(bookImage.getBookImage())) {
            bookFileManager.removeBookImage(bookImage);//managed internally
        }

        BookImage p = this.getById(bookImage.getId());


        this.delete(p);
    }

    @Override
    public void saveOrUpdate(BookImage bookImage) throws ServiceException {
        update(bookImage);
    }

    @Override
    public OutputContentFile getBookImage(String bookCode, String fileName, BookImageSize size) throws ServiceException {
        OutputContentFile outputImage = bookFileManager.getBookImage(bookCode, fileName, size);
        return outputImage;
    }

    @Override
    public void addBookImages(Book book, List<BookImage> bookImages) throws ServiceException {

        try {
            for(BookImage bookImage : bookImages) {

                Assert.notNull(bookImage.getImage());

                InputStream inputStream = bookImage.getImage();
                ImageContentFile cmsContentImage = new ImageContentFile();
                cmsContentImage.setFileName( bookImage.getBookImage() );
                cmsContentImage.setFile( inputStream );
                cmsContentImage.setFileContentType(FileContentType.BOOK);

                addBookImage(book,bookImage,cmsContentImage);
            }

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
