package com.dynamicheart.bookstore.core.repositories.catalog.category;


import com.dynamicheart.bookstore.core.model.catalog.category.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

	List<Object[]> countBooksByCategories(List<Long> categoryIds);

	List<Category> listByParent(Category category);

}
