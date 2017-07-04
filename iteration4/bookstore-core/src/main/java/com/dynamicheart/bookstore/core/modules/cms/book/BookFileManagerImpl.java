package com.dynamicheart.bookstore.core.modules.cms.book;

import com.dynamicheart.bookstore.core.constants.Constants;
import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImageSize;
import com.dynamicheart.bookstore.core.model.content.FileContentType;
import com.dynamicheart.bookstore.core.model.content.ImageContentFile;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
import com.dynamicheart.bookstore.core.utils.BookImageCropUtils;
import com.dynamicheart.bookstore.core.utils.BookImageSizeUtils;
import com.dynamicheart.bookstore.core.utils.CoreConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by dynamicheart on 7/2/2017.
 */
public class BookFileManagerImpl extends BookFileManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookFileManagerImpl.class);

    private BookImagePut uploadImage;
    private BookImageGet getImage;
    private BookImageRemove removeImage;

    private CoreConfiguration configuration;
    
    private final static String BOOK_IMAGE_HEIGHT_SIZE = "BOOK_IMAGE_HEIGHT_SIZE";
    private final static String BOOK_IMAGE_WIDTH_SIZE = "BOOK_IMAGE_WIDTH_SIZE";
    private final static String CROP_UPLOADED_IMAGES ="CROP_UPLOADED_IMAGES";

    @Override
    public void removeBookImage(BookImage bookImage) throws ServiceException {
        this.removeImage.removeBookImage(bookImage);
    }

    @Override
    public void removeBookImages(Book book) throws ServiceException {
        this.removeImage.removeBookImages(book);
    }

    @Override
    public void addBookImage(BookImage bookImage, ImageContentFile contentImage) throws ServiceException {
        try {


            /** copy to input stream **/
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Fake code simulating the copy
            // You can generally do better with nio if you need...
            // And please, unlike me, do something about the Exceptions :D
            byte[] buffer = new byte[1024];
            int len;
            while ((len = contentImage.getFile().read(buffer)) > -1 ) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            // Open new InputStreams using the recorded bytes
            // Can be repeated as many times as you wish
            InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
            InputStream is2 = new ByteArrayInputStream(baos.toByteArray());

            BufferedImage bufferedImage = ImageIO.read(is2);
            contentImage.setFile(is1);

            //set Mine Type
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            contentImage.setMimeType(fileNameMap.getContentTypeFor(contentImage.getFileName()));

            //upload original -- L
            contentImage.setFileContentType(FileContentType.BOOKLG);
            uploadImage.addBookImage(bookImage, contentImage);


            String slargeImageHeight = configuration.getProperty(BOOK_IMAGE_HEIGHT_SIZE);
            String slargeImageWidth = configuration.getProperty(BOOK_IMAGE_WIDTH_SIZE);

            if(!StringUtils.isBlank(slargeImageHeight) && !StringUtils.isBlank(slargeImageWidth)) {



                String contentType = fileNameMap.getContentTypeFor(contentImage.getFileName());
                String extension = null;
                if(contentType!=null) {
                    extension = contentType.substring(contentType.indexOf("/")+1,contentType.length());
                }

                if(extension==null){
                    extension="jpeg";
                }


                int largeImageHeight = Integer.parseInt(slargeImageHeight);
                int largeImageWidth = Integer.parseInt(slargeImageWidth);

                if(largeImageHeight<=0 || largeImageWidth<=0) {
                    String sizeMsg = "Image configuration set to an invalid value [BOOK_IMAGE_HEIGHT_SIZE] " + largeImageHeight + " , [BOOK_IMAGE_WIDTH_SIZE] " + largeImageWidth;
                    LOGGER.error(sizeMsg);
                    throw new ServiceException(sizeMsg);
                }

                if(!StringUtils.isBlank(configuration.getProperty(CROP_UPLOADED_IMAGES)) && configuration.getProperty(CROP_UPLOADED_IMAGES).equals(Constants.TRUE)) {
                    //crop image
                    BookImageCropUtils utils = new BookImageCropUtils(bufferedImage, largeImageWidth, largeImageHeight);
                    if(utils.isCropeable()) {
                        bufferedImage = utils.getCroppedImage();
                    }
                }

                //do not keep a large image for now, just take care of the regular image and a small image

                //resize large
                BufferedImage largeResizedImage = BookImageSizeUtils.resizeWithRatio(bufferedImage, largeImageWidth, largeImageHeight);


                File tempLarge = File.createTempFile(new StringBuilder().append(bookImage.getBook().getId()).append("tmpLarge").toString(), "." + extension );
                ImageIO.write(largeResizedImage, extension, tempLarge);

                FileInputStream isLarge = new FileInputStream(tempLarge);

                ImageContentFile largeContentImage = new ImageContentFile();
                largeContentImage.setFileContentType(FileContentType.BOOK);
                largeContentImage.setFileName(bookImage.getBookImage());
                largeContentImage.setMimeType(contentType);
                largeContentImage.setFile(isLarge);


                uploadImage.addBookImage(bookImage, largeContentImage);
                
                tempLarge.delete();
            } else {
                contentImage.setFileContentType(FileContentType.BOOK);
                contentImage.setMimeType(fileNameMap.getContentTypeFor(contentImage.getFileName()));
                uploadImage.addBookImage(bookImage, contentImage);
            }
            
        } catch (Exception e) {
            throw new ServiceException(e);
        } finally {
            try {
                bookImage.getImage().close();
            } catch(Exception ignore) {}
        }
    }

    @Override
    public OutputContentFile getBookImage(String bookIsbn, String imageName) throws ServiceException {
        return getBookImage(bookIsbn, imageName);
    }

    @Override
    public OutputContentFile getBookImage(String bookIsbn, String imageName, BookImageSize size) throws ServiceException {
        return getImage.getBookImage(bookIsbn, imageName, size);
    }

    @Override
    public OutputContentFile getBookImage(BookImage bookImage) throws ServiceException {
        return getImage.getBookImage(bookImage);
    }

    @Override
    public List<OutputContentFile> getImages(Book book) throws ServiceException {
        return getImage.getImages(book);
    }


    public BookImageRemove getRemoveImage() {
        return removeImage;
    }


    public void setRemoveImage(BookImageRemove removeImage) {
        this.removeImage = removeImage;
    }
    

    public BookImagePut getUploadImage() {
        return uploadImage;
    }


    public void setUploadImage(BookImagePut uploadImage) {
        this.uploadImage = uploadImage;
    }
    

    public BookImageGet getGetImage() {
        return getImage;
    }


    public void setGetImage(BookImageGet getImage) {
        this.getImage = getImage;
    }

    public CoreConfiguration getConfiguration() {
        return configuration;
    }


    public void setConfiguration(CoreConfiguration configuration) {
        this.configuration = configuration;
    }

}
