package com.dynamicheart.bookstore.core.repositories.catalog.category;

import com.dynamicheart.bookstore.core.model.catalog.catagory.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
	

	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cd.seUrl like ?1 order by c.sortOrder asc")
	List<Category> listByFriendlyUrl(String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cd.seUrl=?1")
	Category findByFriendlyUrl(String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cd.name like %?1% and cdl.id=?2order by c.sortOrder asc")
	List<Category> findByName(String name, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.code=?1")
	Category findByCode(String code);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.code in (?1) and cdl.id=?2 order by c.sortOrder asc")
	List<Category> findByCodes(List<String> codes, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.id in (?1) and cdl.id=?2 order by c.sortOrder asc")
	List<Category> findByIds(List<Long> ids, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cdl.id=?2 and c.id = ?1")
	Category findById(Long categoryId, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.id=?1")
	public Category findOne(Long categoryId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.lineage like %?1% order by c.lineage, c.sortOrder asc")
	public List<Category> findByLineage(String linenage);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where c.depth >= ?1 order by c.lineage, c.sortOrder asc")
	public List<Category> findByDepth(int depth);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl where cdl.id=?2 and c.depth >= ?1 order by c.lineage, c.sortOrder asc")
	public List<Category> findByDepth(int depth, Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl left join fetch c.parent cp where cp.id=?1 and cdl.id=?2 order by c.lineage, c.sortOrder asc")
	public List<Category> findByParent(Long parentId, Integer languageId);

}
