package com.dynamicheart.bookstore.dao;

import com.dynamicheart.bookstore.domain.User;

import java.util.List;

/**
 * Created by dynamicheart on 4/19/2017.
 */
public interface UserDAO {
    public List<User> findUsers();

    public List<User> findUsers(Long max, int count);

    public User findOne(Long id);

    public void save(User user);

    public void update(User user);

    public void delete(Long id);
}
