package com.dynamicheart.bookstore.core.modules.cms.book.mongodb;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImageSize;
import com.dynamicheart.bookstore.core.model.content.FileContentType;
import com.dynamicheart.bookstore.core.model.content.ImageContentFile;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
import com.dynamicheart.bookstore.core.modules.cms.book.BookImageGet;
import com.dynamicheart.bookstore.core.modules.cms.book.BookImagePut;
import com.dynamicheart.bookstore.core.modules.cms.book.BookImageRemove;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.util.List;

/**
 * Created by dynamicheart on 7/2/2017.
 */
public class CmsImageFileManagerImpl implements BookImagePut, BookImageGet, BookImageRemove{

    private static final Logger LOGGER = LoggerFactory.getLogger( CmsImageFileManagerImpl.class );

    private static CmsImageFileManagerImpl fileManager = null;

    private GridFsTemplate gridFsTemplate;

    private final static String SMALL = "SMALL";
    private final static String LARGE = "LARGE";

    public final static String TYPE = "TYPE";
    public final static String SIZE = "SIZE";
    public final static String ISBN = "ISBN";

    private final static String BASE_TYPE = "BOOK";

    public static CmsImageFileManagerImpl getInstance() {
        if ( fileManager == null ) {
            fileManager = new CmsImageFileManagerImpl();
        }
        return fileManager;
    }

    private CmsImageFileManagerImpl() {
    }

    @Override
    public void removeBookImage(BookImage bookImage) throws ServiceException {

    }

    @Override
    public void addBookImage(BookImage bookImage, ImageContentFile contentImage) throws ServiceException {
        try{
            DBObject metaData = new BasicDBList();

            //type
            metaData.put(TYPE,BASE_TYPE);

            //book isbn
            metaData.put(ISBN,bookImage.getBook().getIsbn());

            //small large
            if(contentImage.getFileContentType().name().equals(FileContentType.BOOK.name())) {
                metaData.put(SIZE,SMALL);
            } else if(contentImage.getFileContentType().name().equals(FileContentType.BOOKLG.name())) {
                metaData.put(SIZE,LARGE);
            }

            //
            gridFsTemplate.store(contentImage.getFile(),contentImage.getFileName(),contentImage.getMimeType(),metaData);
        }catch (Exception e){

        }
    }

    @Override
    public void removeBookImages(Book book) throws ServiceException {

    }

    @Override
    public OutputContentFile getBookImage(String bookIsbn, String imageName) throws ServiceException {
        return null;
    }

    @Override
    public OutputContentFile getBookImage(String bookIsbn, String imageName, BookImageSize size) throws ServiceException {
        return null;
    }

    @Override
    public OutputContentFile getBookImage(BookImage bookImage) throws ServiceException {
        return null;
    }

    @Override
    public List<OutputContentFile> getImages(Book book) throws ServiceException {
        return null;
    }

    public GridFsTemplate getGridFsTemplate() {
        return gridFsTemplate;
    }

    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }
}
