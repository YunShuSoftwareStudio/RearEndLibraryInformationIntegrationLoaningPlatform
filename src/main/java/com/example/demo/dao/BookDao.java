package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/6 19:54
 */
public interface BookDao extends JpaRepository <Book, String> {
    /**
     * 通过名字查询
     *
     * @param name 名字
     *
     * @return List<Book>
     */
    List <Book> findByNameContaining(String name);

}
