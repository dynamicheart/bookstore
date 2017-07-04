package com.dynamicheart.bookstore.core.modules.cms.book.mongodb;

import com.dynamicheart.bookstore.core.constants.Constants;
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
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
    public void addBookImage(BookImage bookImage, ImageContentFile contentImage) throws ServiceException {
        try{
            List<GridFSDBFile> gridFSDBFiles = gridFsTemplate
                    .find(new Query(Criteria.where("metadata.ISBN").is(bookImage.getBook().getIsbn())
                            .andOperator(Criteria.where("filename").is(contentImage.getFileName()))));

            DBObject metaData = new BasicDBObject();

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


            gridFsTemplate.store(contentImage.getFile(),contentImage.getFileName(),contentImage.getMimeType(),metaData);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeBookImage(BookImage bookImage) throws ServiceException {
        try{
            gridFsTemplate.delete(new Query(Criteria.where("metadata.ISBN").is(bookImage.getBook().getIsbn())
                    .andOperator(Criteria.where("filename").is(bookImage.getBookImage()))));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeBookImages(Book book) throws ServiceException {
        try{
            gridFsTemplate.delete(new Query(Criteria.where("metadata.ISBN").is(book.getIsbn())));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputContentFile getBookImage(String bookIsbn, String imageName) throws ServiceException {

        return getBookImage(bookIsbn, imageName, BookImageSize.SMALL);
    }

    @Override
    public OutputContentFile getBookImage(String bookIsbn, String imageName, BookImageSize size) throws ServiceException {
        OutputContentFile contentImage = new OutputContentFile();
        try{
            List<GridFSDBFile> gridFSDBFiles = gridFsTemplate
                    .find(new Query(Criteria.where("metadata.ISBN").is(bookIsbn)
                            .andOperator(Criteria.where("filename").is(imageName),(Criteria.where("metadata.SIZE").is(BookImageSize.SMALL.name())))));

            GridFSDBFile gridFSDBFile = gridFSDBFiles.get(0);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            IOUtils.copy(gridFSDBFile.getInputStream(), outputStream);

            contentImage.setFile(outputStream);
            contentImage.setMimeType(gridFSDBFile.getContentType());
            contentImage.setFileName(gridFSDBFile.getFilename());

        }catch (Exception e){
            throw new ServiceException(e);
        }finally {

        }

        return contentImage;
    }

    @Override
    public OutputContentFile getBookImage(BookImage bookImage) throws ServiceException {

        return getBookImage(bookImage.getBook().getIsbn(), bookImage.getBookImage());
    }

    @Override
    public List<OutputContentFile> getImages(Book book) throws ServiceException {

        List<OutputContentFile> images = new ArrayList<OutputContentFile>();

        try{
            List<GridFSDBFile> gridFSDBFiles = gridFsTemplate.find(
                    new Query(Criteria.where("metadata.ISBN").is(book.getIsbn())
                            .andOperator(Criteria.where("metadata.TYPE").is(LARGE))));

            for(GridFSDBFile gridFSDBFile:gridFSDBFiles){
                OutputContentFile contentImage = new OutputContentFile();

                InputStream inputStream = gridFSDBFile.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                IOUtils.copy(inputStream,outputStream);

                contentImage.setFile(outputStream);
                contentImage.setMimeType(gridFSDBFile.getContentType());
                contentImage.setFileName(gridFSDBFile.getFilename());

                images.add(contentImage);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }finally {
            return images;
        }
    }

    public GridFsTemplate getGridFsTemplate() {
        return gridFsTemplate;
    }

    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }
}
