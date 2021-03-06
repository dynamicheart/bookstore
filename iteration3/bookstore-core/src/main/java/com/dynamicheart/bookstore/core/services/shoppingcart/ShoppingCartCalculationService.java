/**
 *
 */
package com.dynamicheart.bookstore.core.services.shoppingcart;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;

public interface ShoppingCartCalculationService {

	public void calculate(final ShoppingCart cartModel, final Customer customer,
                                       final Language language) throws ServiceException;


	public void calculate(final ShoppingCart cartModel, final Language language)
			throws ServiceException;
}
