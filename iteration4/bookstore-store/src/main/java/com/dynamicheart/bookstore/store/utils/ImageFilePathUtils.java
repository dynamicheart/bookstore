package com.dynamicheart.bookstore.store.utils;

import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import org.springframework.stereotype.Component;

/**
 * Created by dynamicheart on 7/8/2017.
 */

@Component
public class ImageFilePathUtils extends AbstractimageFilePath{
    private String basePath = StoreConstants.STATIC_URI;

    @Override
    public String getContextPath() {
        return super.getProperties().getProperty(CONTEXT_PATH);
    }

    @Override
    public String getBasePath() {
        return basePath;
    }

    @Override
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
