package com.dynamicheart.bookstore.service;

import com.dynamicheart.bookstore.domain.User;

import java.util.List;

/**
 * Created by dynamicheart on 4/19/2017.
 */
public interface UserService {
    User findOne(Long id);
    List<User> findUsers();
    void update(User user);
    void save(User user);
    void delete(Long id);
    boolean isExist(User user);
}
