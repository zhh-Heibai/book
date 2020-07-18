package com.heibai.dao;

import com.heibai.pojo.Order;

import java.util.List;

public interface OrderDao {
    int saveOrder(Order order);//保存订单
    List<Order> queryAllOrders();//查询所有订单
    List<Order> queryMyOrders(Integer userId);//根据用户id查询订单
    Order queryOrderByOrderId(String orderId);//根据订单id查询订单
    int updateOrder(Order order);
}
