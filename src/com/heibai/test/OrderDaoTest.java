package com.heibai.test;

import com.heibai.dao.OrderDao;
import com.heibai.dao.impl.OrderDaoImpl;
import com.heibai.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoTest {
    private OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("00001",new Date(),new BigDecimal(12),0,1));
    }

    @Test
    public void queryAllOrders() {
        for (Order order: orderDao.queryAllOrders()
             ) {
            System.out.println(order);
        }

    }

    @Test
    public void queryOrderByOrderId() {
        System.out.println(orderDao.queryOrderByOrderId("15936732097812"));
    }
    @Test
    public void queryMyOrders() {
        List<Order> list = orderDao.queryMyOrders(2);
        for (Order order:list
             ) {
            System.out.println(order);
        }
    }
    @Test
    public void updateOrder() {
        orderDao.updateOrder(new Order("00001",new Date(),new BigDecimal(20),1,2));

    }
}