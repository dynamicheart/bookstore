package com.dynamicheart.bookstore.core.repositories.catalog.book;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface BookRepositoryCustom {

    Book getById(Long bookId);

    Book getByCode(String bookCode);

    List<Book> getBooksListByCategories(@SuppressWarnings("rawtypes") Set categoryIds);

    List<Book> getBooksListByCategories(Set<Long> categoryIds,
                                        Language language);

    Book getByFriendlyUrl(String seUrl, Locale locale);

    List<Book> listByCriteria(String criteria);
}
