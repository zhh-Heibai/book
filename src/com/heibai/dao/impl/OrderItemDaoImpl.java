package com.heibai.dao.impl;

import com.heibai.dao.OrderItemDao;
import com.heibai.pojo.OrderItem;

import java.util.List;

/**
 * @author heibai
 * @date 2020/7/1 21:48
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {


    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`totalPrice`,`orderId`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderDetailByOrderId(String orderId) {
        String sql="select  `id` , `name` , `count` , `price` , `totalPrice` , `orderId` from t_order_item where orderId=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
