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

    @Query("select distinct b from Book as b join fetch b.availabilities ba join fetch b.descriptions bd left join fetch b.categories categs left join fetch b.images images left join fetch b.publisher publi left join fetch publi.descriptions publid ")
    List<Book> list();
}
