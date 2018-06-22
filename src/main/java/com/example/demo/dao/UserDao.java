package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/7 13:25
 */
public interface UserDao extends JpaRepository <User, String> {
    /**
     * 通过用户名查找用户
     *
     * @param name 用户姓名
     * @return User
     */
    User findByName(String name);

    /**
     * 通过名字查询
     *
     * @param name 名字
     *
     * @return List<Book>
     */
    List <User> findByNameContaining(String name);

}
