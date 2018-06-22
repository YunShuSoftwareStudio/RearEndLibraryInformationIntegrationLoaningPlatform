package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/9 12:28
 */
public interface UserService {

    /**
     * @param user 用户实体
     * @return User
     */
    User saveUser(User user);

    /**
     * 通过名字查询
     *
     * @param name 用户名字
     * @return List<User>
     */
    List <User> findByNameContaining(String name);

    /**
     * 通过用户名更改密码
     *
     * @param user 用户实体
     * @return User
     */
    User updateUser(User user);
}
