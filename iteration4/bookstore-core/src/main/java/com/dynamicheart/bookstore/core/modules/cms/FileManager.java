package com.dynamicheart.bookstore.core.modules.cms;

import com.dynamicheart.bookstore.core.utils.exception.FileException;
import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.content.InputContentFile;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public interface FileManager {

    String addFile(InputContentFile file) throws FileException;

    void deleteFile(String resourceId) throws FileException;

    OutputContentFile searchFile(String resourceId) throws FileException;

}
