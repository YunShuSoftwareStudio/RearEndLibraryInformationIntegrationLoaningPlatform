package com.example.demo.contller;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.Status;
import com.example.demo.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: 李纹纹
 * @date : 2018/6/9 15:13
 */
@RestController
public class BorrowComtller {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;
    Status status = new Status();

    @GetMapping("borrowFindAll")
    public Status findAll() {
        List <Borrow> borrowServiceAll = borrowService.findAll();
        status.setList(borrowServiceAll);
        status.setCode(200);
        status.setMsg("查询成功");
        return status;
    }

    @GetMapping("borrowId")
    public Status saveUserIDAndBookId(String userId, String bookId, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            if (userId == null || userId.equals("") || bookId == null || bookId.equals("")) {
                status.setCode(202);
                status.setMsg("借书用户ID和书籍ID不能为空");
                return status;
            } else {
                Borrow borrow = new Borrow();
                borrow.setId(UUID.randomUUID().toString());
                borrow.setUserId(userDao.getOne(userId));
                borrow.setBookId(bookDao.getOne(bookId));
                borrowService.saveUserIDAndBookId(borrow);
                status.setCode(200);
                status.setMsg("借书成功");
            }
        } else {
            status.setCode(401);
            status.setMsg("请先登入才可以借书");
        }
        return status;
    }

    @GetMapping("findById")
    public Status findByIdContaining(String userId) {
        if (userId == null || userId.equals("")) {
            status.setCode(202);
            status.setMsg("用户ID不能为空");
            status.setList(null);
        } else {
            if (borrowService.findByIdContaining(userId).size() == 0) {
                status.setCode(202);
                status.setMsg("用户ID不存在");
                status.setList(null);
            } else {
                ArrayList <Book> bookArrayList = borrowService.findByIdContaining(userId).stream().map(Borrow::getBookId).collect(Collectors.toCollection(ArrayList::new));
                status.setList(bookArrayList);
                status.setCode(200);
                status.setMsg("修改成功");
            }
        }
        return status;
    }
}
