package com.dynamicheart.bookstore.core.services.catalog.category;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.catagory.Category;
import com.dynamicheart.bookstore.core.model.catalog.catagory.CategoryDescription;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;

public interface CategoryService extends BookstoreEntityService<Long, Category> {

	List<Category> listByLineage(String lineage) throws ServiceException;
	
	List<Category> listBySeUrl(String seUrl) throws ServiceException;
	
	CategoryDescription getDescription(Category category, Language language) throws ServiceException;

	void addCategoryDescription(Category category, CategoryDescription description) throws ServiceException;

	void addChild(Category parent, Category child) throws ServiceException;

	List<Category> listByParent(Category category) throws ServiceException;

	List<Category> getByName(String name, Language language) throws ServiceException;

	Category getByCode(String code)
			throws ServiceException;

	void saveOrUpdate(Category category) throws ServiceException;

	List<Category> listByDepth(int depth);

	List<Category> listByDepth(int depth, Language language);

	Category getBySeUrl(String seUrl);

	List<Category> listByParent(Category category, Language language);

	Category getByLanguage(long categoryId, Language language);

	List<Object[]> countBooksByCategories(List<Long> categoryIds) throws ServiceException;


	List<Category> listByCodes(List<String> codes, Language language);

	List<Category> listByIds(List<Long> ids, Language language);


	
	

}
