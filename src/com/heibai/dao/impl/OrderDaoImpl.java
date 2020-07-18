package com.heibai.dao.impl;

import com.heibai.dao.OrderDao;
import com.heibai.pojo.Order;

import java.util.List;

/**
 * @author heibai
 * @date 2020/7/1 21:48
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`orderId`,`createTime`,`price`,`status`,`userId`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
    @Override
    public List<Order> queryAllOrders() {
//        String
        String sql="select  `orderId` , `createTime` , `price` , `status` , `userId` from t_order order by createTime";
        return queryForList(Order.class,sql);
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql="select  `orderId` , `createTime` , `price` , `status` , `userId` from t_order where userId=? order by createTime";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql="select `orderId` , `createTime` , `price` , `status` , `userId` from t_order where orderId=?";
        return queryForOne(Order.class,sql, orderId);
    }

    @Override
    public int updateOrder(Order order) {
        String sql="update t_order set `orderId`=?,`createTime`=?,`price`=?,`status`=?,`userId`=? where orderId=?";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId(),order.getOrderId());
    }
}
