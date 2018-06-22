package com.example.demo.service.Impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/9 12:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }


    @Override
    public List<User> findByNameContaining(String name) {
        return userDao.findByNameContaining(name);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }
}
