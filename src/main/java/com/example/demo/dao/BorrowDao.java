package com.example.demo.dao;

import com.example.demo.entity.Borrow;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/9 14:46
 */
public interface BorrowDao extends JpaRepository<Borrow,String> {

    /**
     * 通过用户id查找所借书籍
     * @param user 用户Id
     * @return List<Borrow>

     */
    List<Borrow> findByUserId(User user);
}
