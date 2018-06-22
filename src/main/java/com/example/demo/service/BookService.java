package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/6 19:54
 */
public interface BookService {
    /**
     * 查询全部图书
     *
     * @return 所有书籍
     */
    List <Book> findAll();

    /**
     * 通过id删除图书
     *
     * @param id 图书id
     */
    void deletById(String id);

    /**
     * 通过名字查询
     *
     * @param name 图书名字
     * @return 查询书的详情
     */
    List <Book> findByName(String name);

    /**
     * 添加图书通过名字,价格
     *
     * @param name  图书名字
     * @param price 图书价格
     * @return 添加书籍的列表
     */
    //TODO ****未完待续****,需添加书籍实体

    Book saveBook(String name, Double price);

    /**
     * 修改图书现有名字,价格
     *
     * @param name  图书名字
     * @param price 图书价格
     * @return 修改的图书列表
     */
    Book updateBook(String id,String name, Double price);

    /**
     *通过ID查询书籍
     * @param id 书籍
     * @return Book
     */
    List <Book> findByIdContaining(String id);
}
