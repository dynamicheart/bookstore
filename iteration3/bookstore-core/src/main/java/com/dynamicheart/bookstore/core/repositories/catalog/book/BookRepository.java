package com.dynamicheart.bookstore.core.repositories.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom{
}
