package com.dynamicheart.bookstore.store.controller;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImageSize;
import com.dynamicheart.bookstore.core.model.content.OutputContentFile;
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
	BookImageService bookImageService;
	
	@RequestMapping("/static/books/{bookISBN}/{imageSize}/{imageName}.{extension}")
	public @ResponseBody
    byte[] printImage(@PathVariable final String bookISBN, @PathVariable final String imageSize, @PathVariable final String imageName, @PathVariable final String extension, HttpServletRequest request) throws IOException {
        
		// example small book image -> /static/books/TB12345/SMALL/book1.jpg

        BookImageSize size = BookImageSize.SMALL;

        if(BookImageSize.LARGE.name().equals(imageSize)){
            size = BookImageSize.LARGE;
        }

        OutputContentFile image = null;
        try {
            image = bookImageService.getBookImage(bookISBN, new StringBuilder().append(imageName).append(".").append(extension).toString(), size);
        } catch (ServiceException e) {
            LOGGER.error("Cannot retrieve image " + imageName, e);
        }
        if(image!=null) {
            return image.getFile().toByteArray();
        } else {
            image = bookImageService.getDefaultImage(size);
            return image.getFile().toByteArray();
        }
	}
}
