package com.dynamicheart.bookstore.core.repositories.catalog.category;

import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl")
	List<Category> list();

	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cd.seUrl like ?1")
	List<Category> listByFriendlyUrl(String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cd.seUrl=?1")
    Category findByFriendlyUrl(String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cd.name like ?1 and cdl.id=?2")
	List<Category> findByNameContaining(String name, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.code=?1")
    Category findByCode(String code);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.code in (?1) and cdl.id=?2")
	List<Category> findByCodes(List<String> codes, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.id in (?1) and cdl.id=?2")
	List<Category> findByIds(List<Long> ids, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cdl.id=?2 and c.id = ?1")
    Category findById(Long categoryId, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.id=?1")
	public Category findOne(Long categoryId);

}
