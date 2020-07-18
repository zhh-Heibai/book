package com.heibai.test;

import com.heibai.dao.OrderItemDao;
import com.heibai.dao.impl.OrderItemDaoImpl;
import com.heibai.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {
    private OrderItemDao orderItem=new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItem.saveOrderItem(new OrderItem(null,"jvm深入学习",1,new BigDecimal(12),new BigDecimal(12),"00001"));

    }
    @Test
    public void queryOrderDetailByOrderId() {
        System.out.println(orderItem.queryOrderDetailByOrderId("15936732097812"));
    }
}