package com.dynamicheart.bookstore.core.repositories.catalog.book.image;

import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dynamicheart on 6/6/2017.
 */
public interface BookImageRepository extends JpaRepository<BookImage, Long> {
}
