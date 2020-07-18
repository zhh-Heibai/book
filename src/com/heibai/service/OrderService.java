package com.heibai.service;

import com.heibai.pojo.Cart;
import com.heibai.pojo.Order;
import com.heibai.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);
    List<Order> queryAllOrders();
    List<Order> queryMyOrders(Integer userId);
    List<OrderItem> orderDetail(String orderId);
    void sendOrder(String orderId);
    void receiveOrder(String orderId);
}
