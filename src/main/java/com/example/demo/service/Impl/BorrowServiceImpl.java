package com.example.demo.service.Impl;

import com.example.demo.dao.BorrowDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.User;
import com.example.demo.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: 李纹纹
 * @date : 2018/6/9 14:59
 */
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Borrow saveUserIDAndBookId(Borrow borrow) {
        return borrowDao.save(borrow);
    }

    @Override
    public List <Borrow> findAll() {
        return borrowDao.findAll();
    }

    @Override
    public List <Borrow> findByIdContaining(String userId) {
        User user=userDao.getOne(userId);
        return borrowDao.findByUserId(user);
    }
}
