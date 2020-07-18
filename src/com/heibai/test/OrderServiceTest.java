package com.heibai.test;

import com.heibai.pojo.Cart;
import com.heibai.pojo.CartItem;
import com.heibai.pojo.Order;
import com.heibai.service.OrderService;
import com.heibai.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    private OrderService orderService=new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(2,"java入坑222",1,new BigDecimal(2),new BigDecimal(2)));
        System.out.println(orderService.createOrder(cart,2));
    }
    @Test
    public void queryMyOrders() {
        System.out.println(orderService.queryMyOrders(2));
    }
    @Test
    public void orderDetail() {
        System.out.println(orderService.orderDetail("15936732097812"));
    }
    @Test
    public void queryAllOrders() {
        for (Order order:orderService.queryAllOrders()
             ) {
            System.out.println(order);
        }

    }

}