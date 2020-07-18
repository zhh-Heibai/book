package com.heibai.service.impl;

import com.heibai.dao.OrderDao;
import com.heibai.dao.OrderItemDao;
import com.heibai.dao.impl.OrderDaoImpl;
import com.heibai.dao.impl.OrderItemDaoImpl;
import com.heibai.pojo.*;
import com.heibai.service.BookService;
import com.heibai.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author heibai
 * @date 2020/7/1 22:33
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookService bookService=new BookServiceImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
//        int i=12/0;
        //遍历购物车中每一个商品项转换成为订单项保存在数据库中
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()
             ) {
            //获取每一个购物车的商品项
            CartItem cartItem = entry.getValue();
            //转换成订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookService.queryBookById(cartItem.getId());
            if (book!=null){
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookService.updateBook(book);
            }

        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        List<Order> myOrders = orderDao.queryMyOrders(userId);
        return myOrders;
    }

    @Override
    public List<OrderItem> orderDetail(String orderId) {
        List<OrderItem> orderDetail = orderItemDao.queryOrderDetailByOrderId(orderId);
        return orderDetail;
    }

    @Override
    public List<Order> queryAllOrders() {
        List<Order> allOrders = orderDao.queryAllOrders();
        return allOrders;
    }

    @Override
    public void sendOrder(String orderId) {
        Order order = orderDao.queryOrderByOrderId(orderId);
        if (order!=null){
            order.setStatus(1);
            orderDao.updateOrder(order);
        }
    }

    @Override
    public void receiveOrder(String orderId) {
        Order order = orderDao.queryOrderByOrderId(orderId);
        if (order!=null){
            order.setStatus(2);
            orderDao.updateOrder(order);
        }
    }
}
