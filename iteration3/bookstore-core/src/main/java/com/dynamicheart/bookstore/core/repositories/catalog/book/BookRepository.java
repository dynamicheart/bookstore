package com.dynamicheart.bookstore.core.repositories.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom{

    @Query(value = "select distinct b from Book as b join fetch b.descriptions bd ", countQuery = "select count(distinct b) from Book b")
    Page<Book> findAll(Pageable pageable);

    @Query("select distinct b from Book as b join fetch b.descriptions bd")
    List<Book> list();
}
