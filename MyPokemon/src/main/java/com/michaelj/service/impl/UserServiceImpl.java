package com.michaelj.service.impl;

import com.michaelj.dao.UserDao;
import com.michaelj.domain.User;
import com.michaelj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(User user) {
        return userDao.getByCondition(user);
    }
}
