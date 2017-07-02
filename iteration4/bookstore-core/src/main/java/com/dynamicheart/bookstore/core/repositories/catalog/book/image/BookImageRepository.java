package com.dynamicheart.bookstore.core.repositories.catalog.book.image;

import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dynamicheart on 6/6/2017.
 */
public interface BookImageRepository extends MongoRepository<BookImage, ObjectId> {
}
