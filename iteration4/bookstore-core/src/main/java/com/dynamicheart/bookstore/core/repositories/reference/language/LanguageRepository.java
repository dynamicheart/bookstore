package com.dynamicheart.bookstore.core.repositories.reference.language;


import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
	
	Language findByCode(String code) throws ServiceException;
	


}
