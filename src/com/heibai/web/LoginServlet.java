package com.heibai.web;

import com.heibai.pojo.User;
import com.heibai.service.UserService;
import com.heibai.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //包装成User对象，并调用userService.login处理页面
        User loginUser = userService.login(new User(null, username, password, null));
        //如果等于空，登录失败
        if (loginUser==null){
            //登录失败，重新登录
            //回显到客户端
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {

            //登录成功，保存cookie值并跳转成功的页面
            //保存cookie
            /*Cookie cookie=new Cookie("username",req.getParameter("username"));
            cookie.setMaxAge(60*60*24*7);//7天有效
            resp.addCookie(cookie);*/
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
