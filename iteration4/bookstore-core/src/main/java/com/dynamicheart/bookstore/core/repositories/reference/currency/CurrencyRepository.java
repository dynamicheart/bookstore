package com.dynamicheart.bookstore.core.repositories.reference.currency;

import com.dynamicheart.bookstore.core.model.reference.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	
	Currency getByCode(String code);
}
