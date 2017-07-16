package com.dynamicheart.bookstore.core.services.catalog.category;


import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import com.dynamicheart.bookstore.core.model.catalog.category.CategoryDescription;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;

public interface CategoryService extends BookstoreEntityService<Long, Category> {

	
	List<Category> listBySeUrl(String seUrl) throws ServiceException;
	
	CategoryDescription getDescription(Category category, Language language) throws ServiceException;

	void addCategoryDescription(Category category, CategoryDescription description) throws ServiceException;

	List<Category> getByName(String name, Language language) throws ServiceException;

	Category getByCode(String code)
			throws ServiceException;

	void saveOrUpdate(Category category) throws ServiceException;


	Category getBySeUrl(String seUrl);

	Category getByLanguage(long categoryId, Language language);

	List<Object[]> countBooksByCategories(List<Long> categoryIds) throws ServiceException;

	List<Category> listByCodes(List<String> codes, Language language);

	List<Category> listByIds(List<Long> ids, Language language);


	
	

}
