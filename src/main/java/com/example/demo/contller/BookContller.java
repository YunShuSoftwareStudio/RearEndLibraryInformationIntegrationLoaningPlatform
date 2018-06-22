package com.example.demo.contller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Status;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/6 19:58
 */
@RestController
public class BookContller {

    @Autowired
    private BookService bookService;

    Status status = new Status();


    /**
     * 查找全部书籍
     *
     * @return
     */
    @GetMapping("/findAllBook")
    public Status findAll() {
        try {
            List <Book> list = bookService.findAll();
            status.setList(list);
            status.setCode(200);
            status.setMsg("查询成功");
        } catch (Exception e) {
            status.setCode(500);
            status.setMsg(e.getMessage());
            status.setList(null);
        }
        return status;
    }

    /**
     * 通过id删除书籍
     *
     * @param id
     * @return
     */
    @GetMapping("deleteBook")
    public Status deleteById(String id) {
        try {
            bookService.deletById(id);
            status.setCode(200);
            status.setMsg("删除成功");
            status.setList(null);
        } catch (Exception e) {
            status.setCode(500);
            status.setMsg(e.getMessage());
            status.setList(null);
        }
        return status;
    }

    /**
     * 按名字查找图书
     *
     * @param name
     * @return
     */
    @GetMapping("findBookByName")
    public Status findName(String name) {
        try {
            if (name == null || name.equals("")) {
                status.setCode(202);
                status.setMsg("书名不能为空");
                status.setList(null);
            } else {
                List <Book> byName = bookService.findByName(name);
                if (byName.size() == 0) {
                    status.setCode(204);
                    status.setMsg("没有此书请重新输入");
                    status.setList(null);
                } else {
                    status.setList(byName);
                    status.setCode(200);
                    status.setMsg("查找成功");
                }
            }
        } catch (Exception e) {
            status.setCode(500);
            status.setMsg(e.getMessage());
            status.setList(null);
        }
        return status;
    }

    /**
     * 添加书名,价格
     *
     * @param name
     * @param price
     * @return
     */
    @GetMapping("saveBook")
    public Status saveBook(String name, Double price) {
        try {
            if (name == null || name.equals("")) {
                status.setCode(202);
                status.setMsg("书名不能为空");
                status.setList(null);
            } else {
                if (price == null || price.equals("")) {
                    status.setCode(202);
                    status.setMsg("价格不能为空");
                    status.setList(null);
                } else {
                    //TODO sin码重复不准添加,需要修改!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    List <Book> byName = bookService.findByName(name);
                    if (byName.size() == 0) {
                        bookService.saveBook(name, price);
                        status.setCode(200);
                        status.setMsg("添加成功");
                        status.setList(null);
                    } else {
                        status.setCode(203);
                        status.setMsg("此书重复添加");
                        status.setList(null);
                    }
                }
            }
        } catch (Exception e) {
            status.setCode(500);
            status.setMsg(e.getMessage());
        }
        return status;
    }

    @GetMapping("updateBook")
    public Status update(String id, String name, Double price) {
        if (id == null || id.equals("")) {
            status.setCode(202);
            status.setMsg("修改id不能为空");
            status.setList(null);
        } else {
            if (name == null || name.equals("")) {
                status.setCode(202);
                status.setMsg("修改名字不能为空");
                status.setList(null);
            } else {
                if (price == null || price.equals("")) {
                    status.setCode(202);
                    status.setMsg("修改书籍价格不能为空");
                    status.setList(null);
                } else {
                    List <Book> byIdContaining = bookService.findByIdContaining(id);
                    if (byIdContaining.size() == 0) {
                        status.setCode(204 );
                        status.setMsg("没有此书id");
                        status.setList(null);
                    } else {
                        bookService.updateBook(id, name, price);
                        status.setCode(200);
                        status.setMsg("修改书籍成功");
                        status.setList(null);
                    }
                }
            }
        }
        return status;
    }
}
