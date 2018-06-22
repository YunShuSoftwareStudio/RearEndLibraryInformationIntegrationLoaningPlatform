package com.example.demo.service.Impl;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author: 李纹纹
 * @date : 2018/6/6 19:56
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void deletById(String id) {

        if (!bookDao.existsById(id)){
            throw new NullPointerException("没有此id");
        }
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookDao.findByNameContaining(name);
    }

    @Override
    public Book saveBook(String name, Double price) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(name);
        book.setPrice(price);
        return bookDao.save(book);
    }

    @Override
    public Book updateBook( String id,String name, Double price) {
            Book book = new Book();
            book.setId(id);
            book.setName(name);
            book.setPrice(price);
            return bookDao.save(book);
    }

    @Override
    public List <Book> findByIdContaining(String id) {
        return bookDao.findAllById(Collections.singleton(id));
    }
}
