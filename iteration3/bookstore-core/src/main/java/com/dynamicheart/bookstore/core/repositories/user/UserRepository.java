package com.dynamicheart.bookstore.core.repositories.user;

import com.dynamicheart.bookstore.core.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select u from User as u inner join fetch u.groups ug where u.adminName = ?1")
    User findByUserName(String userName);

    @Query("select u from User as u inner join fetch u.groups ug where u.id = ?1")
    User findOne(Long id);

    @Query("select u from User as u inner join fetch u.groups ug order by u.id")
    List<User> findAll();
}
