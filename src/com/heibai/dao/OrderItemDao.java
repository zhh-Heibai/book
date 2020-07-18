package com.heibai.dao;

import com.heibai.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
    List<OrderItem> queryOrderDetailByOrderId(String orderId);
    //更改
}
