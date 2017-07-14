package com.dynamicheart.bookstore.core.modules.cms.mongodb;

import com.dynamicheart.bookstore.core.utils.exception.FileException;
import com.dynamicheart.bookstore.core.model.content.InputContentFile;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
import com.dynamicheart.bookstore.core.modules.cms.FileManager;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;

/**
 * Created by dynamicheart on 7/5/2017.
 */

@Component
public class MongodbFileMangerImpl implements FileManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongodbFileMangerImpl.class);

    private static final String SEARCH_KEY = "_id";

    @Qualifier("gridFsTemplate")
    private GridFsTemplate gridFsTemplate;


    @Override
    public String addFile(InputContentFile file) throws FileException{
        try{
            return gridFsTemplate.store(file.getFile(), file.getFileName(), file.getMimeType()).getId().toString();
        }catch (Exception e){
            throw new FileException(e);
        }finally {
            try {
                file.getFile().close();
            } catch(Exception ignore) {}
        }
    }

    @Override
    public void deleteFile(String resourceId) throws FileException{
        try {
            gridFsTemplate.delete(new Query(Criteria.where(SEARCH_KEY).is(resourceId)));
        }catch (Exception e){
            throw new FileException(e);
        }

    }


    @Override
    public OutputContentFile searchFile(String resourceId) throws FileException{
        try{
            OutputContentFile file = new OutputContentFile();
            GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query(Criteria.where(SEARCH_KEY).is(resourceId)));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if(gridFSDBFile != null){
                IOUtils.copy(gridFSDBFile.getInputStream(), outputStream);
                file.setFile(outputStream);
                file.setFileName(gridFSDBFile.getFilename());
                file.setMimeType(gridFSDBFile.getContentType());
            }
            return file;
        }catch(Exception e){
            LOGGER.error("can't find file..");
            throw new FileException(e);
        }

    }


    public GridFsTemplate getGridFsTemplate() {
        return gridFsTemplate;
    }

    @Inject
    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }
}
