package com.heibai.web;

import com.heibai.pojo.User;
import com.heibai.service.UserService;
import com.heibai.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
     private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if ("abcde".equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");
//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(new User(null, username, password, email));
//
//        跳到注册成功页面 regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req, resp);
            }
        } else {
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
        }*/
      //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if (code.equalsIgnoreCase("abcdef")){
            //如果验证码输入正确，检查用户名是否已经被注册
            if (userService.existsUsername(username)){
                System.out.println("用户名"+username+"已经存在");
                //回显到浏览器
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.setAttribute("password",password);
                //用户名已经存在则重新跳转到注册页面

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {

                userService.registUser(new User(null,username,password,email));
                //跳转到注册成功页面
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req,resp);
            }
        }
        else {
            //验证码输入错误，跳转注册页面
            System.out.println("验证输入错误");
            //回显给客户端
            req.setAttribute("msg","验证码输入错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
        }
    }
}
