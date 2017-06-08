package com.dynamicheart.bookstore.store.controller;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
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


	@RequestMapping("/static/files/{imageType}/{imageName}.{extension}")
	public @ResponseBody
    byte[] printImage(@PathVariable final String storeCode, @PathVariable final String imageType, @PathVariable final String imageName, @PathVariable final String extension) throws IOException, ServiceException {

        return null;
	}
	

	@RequestMapping("/static/{imageType}/{productCode}/{imageName}.{extension}")
	public @ResponseBody
    byte[] printImage(@PathVariable final String storeCode, @PathVariable final String productCode, @PathVariable final String imageType, @PathVariable final String imageName, @PathVariable final String extension) throws IOException {

		// product image
		// example small product image -> /static/products/TB12345/product1.jpg
		
		// example large product image -> /static/products/TB12345/product1.jpg

		return null;

	}
	

	@RequestMapping("/static/products/{productCode}/{imageSize}/{imageName}.{extension}")
	public @ResponseBody
    byte[] printImage(@PathVariable final String storeCode, @PathVariable final String productCode, @PathVariable final String imageSize, @PathVariable final String imageName, @PathVariable final String extension, HttpServletRequest request) throws IOException {

		// product image small
		// example small product image -> /static/products/TB12345/SMALL/product1.jpg
		
		// example large product image -> /static/products/TB12345/LARGE/product1.jpg
        return null;

	}
	

	@RequestMapping("/static/products/{productCode}/{imageName}.{extension}")
	public @ResponseBody
    byte[] printImage(@PathVariable final String storeCode, @PathVariable final String productCode, @PathVariable final String imageName, @PathVariable final String extension, HttpServletRequest request) throws IOException {

        // product image
        // example small product image -> /static/products/TB12345/product1.jpg?size=small

        // example large product image -> /static/products/TB12345/product1.jpg
        // or
        //example large product image -> /static/products/TB12345/product1.jpg?size=large

        return null;
    }

}
