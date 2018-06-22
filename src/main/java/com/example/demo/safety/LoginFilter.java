package com.example.demo.safety;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 李纹纹
 * @date : 2018/6/7 14:52
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {
    private final UserDao userDao;

    @Autowired
    public LoginFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT");
        resp.setHeader("Access-Control-Allow-Headers", "*");

        Status status = new Status();
        if ("/login".equals(req.getServletPath())) {
            if (!"POST".equals(req.getMethod())) {
                status.setCode(403);
                status.setMsg("仅支持POST请求");
                String s = mapper.writeValueAsString(status);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().write(s);
                return;
            }
            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");
            User user = userDao.findByName(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    req.getSession().setAttribute("user", user.getId());
                    req.getSession().setMaxInactiveInterval(3600);
                    status.setCode(200);
                    status.setMsg("登陆成功");
                    String s = mapper.writeValueAsString(status);
                    resp.setCharacterEncoding("utf-8");
                    resp.getWriter().write(s);
                } else {
                    status.setCode(400);
                    status.setMsg("密码错误");
                    String s = mapper.writeValueAsString(status);
                    resp.setCharacterEncoding("utf-8");
                    resp.getWriter().write(s);
                }
            } else {
                status.setCode(404);
                status.setMsg("用户名不存在");
                String s = mapper.writeValueAsString(status);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().write(s);
            }
        } else {
            if (req.getSession().getAttribute("user") != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                status.setCode(403);
                status.setMsg("请登录");
                resp.setCharacterEncoding("utf-8");
                String s = mapper.writeValueAsString(status);
                resp.getWriter().write(s);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
