package com.dynamicheart.bookstore.core.services.shoppingcart;


import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.order.OrderTotalSummary;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("shoppingCartCalculationService")
public class ShoppingCartCalculationServiceImpl implements ShoppingCartCalculationService {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Inject
	private ShoppingCartService shoppingCartService;

	@Inject
	private OrderService orderService;

	@Override
	public OrderTotalSummary calculate(final ShoppingCart cartModel, final Customer customer,
                                       final Language language) throws ServiceException {

		Validate.notNull(cartModel, "cart cannot be null");
		Validate.notNull(cartModel.getLineItems(), "Cart should have line items.");
		Validate.notNull(customer, "Customer cannot be null");
		OrderTotalSummary orderTotalSummary = orderService.calculateShoppingCartTotal(cartModel, customer, language);
		updateCartModel(cartModel);
		return orderTotalSummary;

	}

	@Override
	public OrderTotalSummary calculate(final ShoppingCart cartModel, final Language language)
			throws ServiceException {

		Validate.notNull(cartModel, "cart cannot be null");
		Validate.notNull(cartModel.getLineItems(), "Cart should have line items.");
		OrderTotalSummary orderTotalSummary = orderService.calculateShoppingCartTotal(cartModel, language);
		updateCartModel(cartModel);
		return orderTotalSummary;

	}

	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	private void updateCartModel(final ShoppingCart cartModel) throws ServiceException {
		shoppingCartService.saveOrUpdate(cartModel);
	}

}
