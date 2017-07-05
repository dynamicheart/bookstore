package com.dynamicheart.bookstore.core.services.catalog.book.image;

import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.repositories.catalog.book.image.BookImageRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 6/6/2017.
 */

@Service("bookImage")
public class BookImageServiceImpl extends BookstoreEntityServiceImpl<Long, BookImage> implements BookImageService{
    private BookImageRepository bookImageRepository;

    @Inject
    public BookImageServiceImpl(BookImageRepository bookImageRepository) {
        super(bookImageRepository);
        this.bookImageRepository = bookImageRepository;
    }
}
