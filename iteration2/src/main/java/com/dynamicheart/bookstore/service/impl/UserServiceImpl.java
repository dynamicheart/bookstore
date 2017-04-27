package com.dynamicheart.bookstore.service.impl;

import com.dynamicheart.bookstore.dao.UserDAO;
import com.dynamicheart.bookstore.domain.User;
import com.dynamicheart.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dynamicheart on 4/19/2017.
 */

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public User findOne(Long id) {
        return userDAO.findOne(id);
    }

    @Override
    public List<User> findUsers() {
        return userDAO.findUsers();
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public boolean isExist(User user) {
        return false;
    }
}
