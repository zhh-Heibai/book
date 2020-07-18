package com.heibai.web;

import com.heibai.pojo.Cart;
import com.heibai.pojo.Order;
import com.heibai.pojo.OrderItem;
import com.heibai.pojo.User;
import com.heibai.service.OrderService;
import com.heibai.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        }
        else {
            Integer userId = user.getId();
            String orderId=orderService.createOrder(cart,userId);
            req.getSession().setAttribute("orderId",orderId);
//            req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req,resp);
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }

    }

    /**
     * 我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        }
        else {
            Integer userId = user.getId();
            List<Order> myOrders = orderService.queryMyOrders(userId);
            req.getSession().setAttribute("myOrders",myOrders);
//            req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req,resp);
            resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
        }
    }

    /**
     * 订单明细
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得orderId参数
        String orderId = req.getParameter("orderId");
        //获得订单明细列表
        List<OrderItem> orderDetail = orderService.orderDetail(orderId);
        req.getSession().setAttribute("orderDetail",orderDetail);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/pages/order/orderDetail.jsp");
    }

    /**
     * 所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> allOrders = orderService.queryAllOrders();
        req.getSession().setAttribute("allOrders",allOrders);
        resp.sendRedirect(req.getContextPath()+"/pages/manager/order_manager.jsp");
    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect("orderServlet?action=allOrders");
//        req.getRequestDispatcher("pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        resp.sendRedirect("orderServlet?action=myOrders");
    }
}
