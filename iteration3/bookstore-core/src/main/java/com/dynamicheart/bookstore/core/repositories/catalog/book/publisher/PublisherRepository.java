package com.dynamicheart.bookstore.core.repositories.catalog.book.publisher;

import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	@Query("select count(distinct p) from Book as b where b.publisher.id=?1")
	Long countByBook(Long publisherId);
	
	@Query("select p from Publisher p left join p.descriptions pd where p.id=?1")
	Publisher findOne(Long id);

    @Query("select p from Publisher p left join p.descriptions pd where p.code=?1")
    Publisher findByCode(String code);
}
