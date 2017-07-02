package com.dynamicheart.bookstore.core.services.shoppingcart;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCartItem;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityService;

import java.util.List;

public interface ShoppingCartService extends BookstoreEntityService<Long, ShoppingCart> {

	ShoppingCart getShoppingCart(Customer customer) throws ServiceException;

	void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException;

	ShoppingCart getByCode(String code) throws ServiceException;

	ShoppingCart getByCustomer(Customer customer) throws ServiceException;

	boolean isFreeShoppingCart(ShoppingCart cart) throws ServiceException;

	boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws ServiceException;

	ShoppingCartItem populateShoppingCartItem(Book book) throws ServiceException;

	void deleteCart(ShoppingCart cart) throws ServiceException;

	void removeShoppingCart(ShoppingCart cart) throws ServiceException;

	public ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart, final ShoppingCart sessionCart) throws Exception;

	void deleteShoppingCartItem(Long id);

}