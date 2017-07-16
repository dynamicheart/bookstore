package com.dynamicheart.bookstore.core.repositories.customer;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom{

    @Query("select c from  Customer c left join fetch c.avatars cas left join fetch c.defaultLanguage cl left join fetch c.groups where c.id = ?1")
    Customer findOne(Long id);

    @Query("select c from Customer c left join fetch c.avatars cas left join fetch c.defaultLanguage cl left join fetch c.groups  where c.nick = ?1")
    Customer findByNick(String nick);
}
