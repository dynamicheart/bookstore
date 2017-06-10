package com.dynamicheart.bookstore.core.services.catalog.category;

import com.dynamicheart.bookstore.core.constants.Constants;
import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.catagory.Category;
import com.dynamicheart.bookstore.core.model.catalog.catagory.CategoryDescription;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.repositories.catalog.category.CategoryRepository;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceJpaImpl;
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
public class CategoryServiceImpl extends BookstoreEntityServiceJpaImpl<Long, Category> implements CategoryService {
	
	 private CategoryRepository categoryRepository;
	
	 @PersistenceContext(unitName = "shopizerContainer")
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
		
		StringBuilder lineage = new StringBuilder();
		Category parent = category.getParent();
		if(parent!=null && parent.getId()!=null && parent.getId()!=0) {
			lineage.append(parent.getLineage()).append("/").append(parent.getId());
			category.setDepth(parent.getDepth()+1);
		} else {
			lineage.append("/");
			category.setDepth(0);
		}
		category.setLineage(lineage.toString());
		super.update(category);
		
		
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
	public List<Category> listByLineage(String lineage) throws ServiceException {
		try {
			return categoryRepository.findByLineage(lineage);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	

	@Override
	public List<Category> listBySeUrl(String seUrl) throws ServiceException{
		
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
	public List<Category> listByParent(Category category) throws ServiceException {
		
		try {
			return categoryRepository.listByParent(category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<Category> listByParent(Category category, Language language) {
		Assert.notNull(category, "Category cannot be null");
		Assert.notNull(language, "Language cannot be null");
		
		return categoryRepository.findByParent(category.getId(), language.getId());
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
		
		//get category with lineage (subcategories)
		StringBuilder lineage = new StringBuilder();
		lineage.append(category.getLineage()).append(category.getId()).append(Constants.SLASH);
		List<Category> categories = this.listByLineage(lineage.toString());
		
		Category dbCategory = this.getById( category.getId() );
		
		
		if(dbCategory != null && dbCategory.getId().longValue() == category.getId().longValue() ) {			
			
			
			categories.add(dbCategory);
			
			
			Collections.reverse(categories);
			
			List<Long> categoryIds = new ArrayList<Long>();
	
				
			for(Category c : categories) {		
					categoryIds.add(c.getId());
			}

			List<Book> books = bookService.getBooks(categoryIds);
			org.hibernate.Session session = em.unwrap(org.hibernate.Session.class);//need to refresh the session to update all book categories

			
			for(Book book : books) {
				session.evict(book);//refresh book so we get all book categories
				Book dbBook = bookService.getById(book.getId());
				Set<Category> bookCategories = dbBook.getCategories();
				if(bookCategories.size()>1) {
					for(Category c : categories) {
						bookCategories.remove(c);
						bookService.update(dbBook);
					}
					
					if(book.getCategories()==null || book.getCategories().size()==0) {
						bookService.delete(dbBook);
					}
					
				} else {
					bookService.delete(dbBook);
				}
				
				
			}
			
			Category categ = this.getById(category.getId());
			categoryRepository.delete(categ);
			
		}
		
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
	public void addChild(Category parent, Category child) throws ServiceException {
		
		
		if(child==null) {
			throw new ServiceException("Child category should not be null");
		}
		
		try {
			
			if(parent==null) {
				
				//assign to root
				child.setParent(null);
				child.setDepth(0);
				//child.setLineage(new StringBuilder().append("/").append(child.getId()).append("/").toString());
				child.setLineage("/");
				
			} else {
				
				Category p = this.getById(parent.getId());//parent
				
				

				
				String lineage = p.getLineage();
				int depth = p.getDepth();//TODO sometimes null
				
				child.setParent(p);
				child.setDepth(depth+1);
				child.setLineage(new StringBuilder().append(lineage).append(p.getId()).append("/").toString());
				
				
			}
			

			update(child);
			StringBuilder childLineage = new StringBuilder();
			childLineage.append(child.getLineage()).append(child.getId()).append("/");
			List<Category> subCategories = listByLineage(childLineage.toString());
			
			
			//ajust all sub categories lineages
			if(subCategories!=null && subCategories.size()>0) {
				for(Category subCategory : subCategories) {
					if(child.getId()!=subCategory.getId()) {
						addChild(child, subCategory);
					}
				}
				
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		

	}
	
	@Override
	public List<Category> listByDepth(int depth) {
		return categoryRepository.findByDepth(depth);
	}
	
	@Override
	public List<Category> listByDepth(int depth, Language language) {
		return categoryRepository.findByDepth(depth, language.getId());
	}

	@Override
	public List<Category> getByName(String name, Language language) throws ServiceException {
		
		try {
			return categoryRepository.findByName(name, language.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}


}
