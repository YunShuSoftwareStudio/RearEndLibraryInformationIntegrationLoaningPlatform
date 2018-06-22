package com.example.demo.contller;

import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author: 李纹纹
 * @date : 2018/6/9 12:32
 */
@RestController
public class UserContller {
    @Autowired
    private UserService userService;
    Status status = new Status();

    /**
     * 登陆地址
     *
     * @return
     */
    @GetMapping("/")
    public Status login() {
        status.setCode(200);
        status.setMsg("登陆成功");
        return status;
    }

    @GetMapping("saveUser ")
    public Status saveUser(String name, String password, String prompt) {
        User user = new User();
        if (name == null || name.equals("")) {
            status.setCode(202);
            status.setMsg("用户名不能为空");
            status.setList(null);
        } else {
            if (password == null || password.equals("")) {
                status.setCode(202);
                status.setMsg("密码不能为空");
                status.setList(null);
            } else {
                if (prompt == null || prompt.equals("")) {
                    status.setCode(202);
                    status.setMsg("用户密保不能为空");
                    status.setList(null);
                } else {
                    List <User> byNameContaining = userService.findByNameContaining(name);
                    if (byNameContaining.size() == 0) {
                        user.setId(UUID.randomUUID().toString());
                        user.setName(name);
                        user.setPassword(password);
                        user.setPrompt(prompt);
                        userService.saveUser(user);
                        status.setCode(200);
                        status.setMsg("用户注册成功");
                        status.setList(null);
                    } else {
                        status.setCode(203);
                        status.setMsg("用户名重复,请重新输入");
                        status.setList(null);
                    }
                }
            }
        }
        return status;
    }


    @GetMapping("updateUser")
    public Status updateUser(String name, String password, String prompt) {
        if (name == null || name.equals("")) {
            status.setCode(202);
            status.setMsg("修改的用户名不能为空");
            status.setList(null);
        } else {
            if (password == null || password.equals("")) {
                status.setCode(202);
                status.setMsg("修改的密码不能为空");
                status.setList(null);
            } else {
                if (prompt == null || prompt.equals("")){
                    status.setCode(202);
                    status.setMsg("验证不能为空");
                    status.setList(null);
                }else {
                    List <User> byNameContaining = userService.findByNameContaining(name);
                    if (byNameContaining.size() == 0) {
                        status.setCode(204);
                        status.setMsg("请输入正确的要修改用户名");
                        status.setList(null);
                    } else {
                        byNameContaining.forEach(user -> {
                            String prompt1 = user.getPrompt();
                            if (prompt1.equals(prompt)) {
                                user.setName(name);
                                user.setPassword(password);
                                userService.updateUser(user);
                                status.setCode(200);
                                status.setMsg("用户修改成功");
                                status.setList(null);
                            } else {
                                status.setCode(401);
                                status.setMsg("用户验证失败,请重新验证");
                                status.setList(null);
                            }
                        });
                    }
                }
            }

        }
        return status;
    }
}
