package com.dynamicheart.bookstore.core.services.catalog.category;

import com.dynamicheart.bookstore.core.constants.CoreConstants;
import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import com.dynamicheart.bookstore.core.model.catalog.category.CategoryDescription;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.repositories.catalog.category.CategoryRepository;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service("categoryService")
public class CategoryServiceImpl extends BookstoreEntityServiceImpl<Long, Category> implements CategoryService {
	
	 private CategoryRepository categoryRepository;
	
	 @PersistenceContext(unitName = "bookstoreContainer")
	 private EntityManager em;
	  
	 @Inject
	 private BookService bookService;
	  

	
	@Inject
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super(categoryRepository);
		this.categoryRepository = categoryRepository;
	}
	
	public void create(Category category) throws ServiceException {
		
		super.create(category);
		super.update(category);
		
		
	}

	@Override
	public List<Category> list() {
		return categoryRepository.list();
	}

	@Override
	public List<Object[]> countBooksByCategories(List<Long> categoryIds) throws ServiceException {
		
		return categoryRepository.countBooksByCategories(categoryIds);
		
	}
	
	@Override
	public List<Category> listByCodes(List<String> codes,
                                      Language language) {
		return categoryRepository.findByCodes(codes, language.getId());
	}
	
	@Override
	public List<Category> listByIds(List<Long> ids,
                                    Language language) {
		return categoryRepository.findByIds(ids, language.getId());
	}
	
	@Override
	public Category getByLanguage(long categoryId, Language language) {
		return categoryRepository.findById(categoryId, language.getId());
	}
	
	@Override
	public void saveOrUpdate(Category category) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(category.getId()!=null && category.getId()>0) {

			super.update(category);
			
		} else {
			
			super.save(category);
			
		}
		
	}

	@Override
	public List<Category> listBySeUrl(String seUrl) throws ServiceException {
		
		try {
			return categoryRepository.listByFriendlyUrl(seUrl);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public Category getBySeUrl(String seUrl) {
		return categoryRepository.findByFriendlyUrl(seUrl);
	}
	
	
	@Override
	public Category getByCode(String code) throws ServiceException {
		
		try {
			return categoryRepository.findByCode(code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	
	@Override
	public Category getById(Long id) {

			return categoryRepository.findOne(id);
		
	}

	@Override
	public void addCategoryDescription(Category category, CategoryDescription description)
			throws ServiceException {
		
		
		
		try {
			category.getDescriptions().add(description);
			description.setCategory(category);
			update(category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	
	//@Override
	public void delete(Category category) throws ServiceException {
		categoryRepository.delete(category);
	}


	@Override
	public CategoryDescription getDescription(Category category, Language language) {
		
		
		for (CategoryDescription description : category.getDescriptions()) {
			if (description.getLanguage().equals(language)) {
				return description;
			}
		}
		return null;
	}

	@Override
	public List<Category> getByName(String name, Language language) throws ServiceException {
		
		try {
			return categoryRepository.findByNameContaining(name, language.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}


}
