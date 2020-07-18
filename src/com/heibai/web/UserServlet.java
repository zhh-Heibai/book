package com.heibai.web;

import com.google.gson.Gson;
import com.heibai.pojo.User;
import com.heibai.service.UserService;
import com.heibai.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    /**
     * 注销
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //包装成User对象，并调用userService.login处理页面
        User loginUser = userService.login(new User(null, username, password, null));
        //如果等于空，登录失败
        if (loginUser==null){
            //登录失败，重新登录
            //回显到客户端
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            //登录成功调转成功的页面
            request.getSession().setAttribute("user",loginUser);
            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request,response);
        }
    }

    /**
     * ajax验证用户名是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String,Object> map=new HashMap<>();
        String username = request.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Gson gson = new Gson();
        map.put("existsUsername",existsUsername);
        String mapJsonString = gson.toJson(map);
        response.getWriter().write(mapJsonString);
    }
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String repwd=request.getParameter("repwd");
        //获取session中验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        WebUtils.copyParamToBean(request,user);
        //删除session中的验证码
        request.removeAttribute(KAPTCHA_SESSION_KEY);
//       User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        if (token!=null&& token.equalsIgnoreCase(code)){
            //如果验证码输入正确，检查用户名是否已经被注册
            if (userService.existsUsername(username)){
                System.out.println("用户名"+username+"已经存在");
                //回显到浏览器
                request.setAttribute("msg","用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                request.setAttribute("password",password);
                request.setAttribute("repwd",repwd);
                //用户名已经存在则重新跳转到注册页面

                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else {

                userService.registUser(new User(null,username,password,email));
                //跳转到注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }


        else {
            //验证码输入错误，跳转注册页面
            System.out.println("验证输入错误");
            //回显给客户端
            request.setAttribute("msg","验证码输入错误");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            request.setAttribute("password",password);
            request.setAttribute("repwd",repwd);
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request,response);
        }
    }


}
