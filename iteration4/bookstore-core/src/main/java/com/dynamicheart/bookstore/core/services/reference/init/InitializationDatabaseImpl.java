package com.dynamicheart.bookstore.core.services.reference.init;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;

@Service("initializationDatabase")
public class InitializationDatabaseImpl implements InitializationDatabase {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitializationDatabaseImpl.class);

	@Inject
	private LanguageService languageService;

	@Inject
	@Qualifier("datasource")
	private DataSource dataSource;

	@Inject
	private EntityManager em;

	private String name;

	public boolean isEmpty() {
		return languageService.count() == 0;
	}

	@Transactional
	public void populate(String contextName) throws ServiceException {
		this.name =  contextName;
		createLanguages();
	}

	private void createLanguages() throws ServiceException {
		LOGGER.info(String.format("%s : Populating Languages ", name));
		for(String code : SchemaConstant.LANGUAGE_ISO_CODE) {
			Language language = new Language(code);
			languageService.create(language);
		}
	}

}
