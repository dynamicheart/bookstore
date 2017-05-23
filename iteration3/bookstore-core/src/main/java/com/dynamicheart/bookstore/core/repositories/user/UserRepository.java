package com.dynamicheart.bookstore.core.repositories.user;

import com.dynamicheart.bookstore.core.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dynamicheart on 5/23/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUserName(String userName);
}
