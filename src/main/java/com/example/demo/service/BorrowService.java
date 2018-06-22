package com.example.demo.service;

import com.example.demo.entity.Borrow;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/9 14:57
 */
public interface BorrowService {
    /**
     * 存储 USER 和 BOOK 外键
     * @param borrow 实体类
     * @return Borrow
     */
    Borrow saveUserIDAndBookId(Borrow borrow);

    /**
     * 查询全部借书
     * @return  List <Borrow>
     */
    List <Borrow> findAll();

    /**
     * 通过名字查询
     *
     * @param userId 用户Id
     *
     * @return List<Borrow>
     */
    List<Borrow> findByIdContaining(String userId);
}
