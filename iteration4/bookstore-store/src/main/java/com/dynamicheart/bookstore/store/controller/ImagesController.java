package com.dynamicheart.bookstore.store.controller;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
import com.dynamicheart.bookstore.core.modules.cms.FileManager;
import com.dynamicheart.bookstore.core.services.catalog.book.image.BookImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;


@Controller
public class ImagesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImagesController.class);

	@Inject
	private FileManager fileManager;

	@RequestMapping("/static/image/{resourceId}")
	@ResponseBody
	public
	byte[] printImage(@PathVariable final String resourceId, HttpServletRequest request) throws IOException {

		OutputContentFile image = null;

		try {
			image = fileManager.searchFile(resourceId);
		} catch (Exception e) {
			LOGGER.error("Cannot retrieve image ", e);
		}
		if(image!=null && image.getFile() !=null) {
			return image.getFile().toByteArray();
		} else {
			return null;
		}
	}

}
