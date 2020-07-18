package com.heibai.test;

import com.heibai.pojo.Cart;
import com.heibai.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    private Cart cart=new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(2,"java入坑222",1,new BigDecimal(2),new BigDecimal(2)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(2,"java入坑222",1,new BigDecimal(2),new BigDecimal(2)));
        System.out.println(cart);
        System.out.println("--------------");
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(2,"java入坑222",1,new BigDecimal(2),new BigDecimal(2)));
        System.out.println(cart);
        System.out.println("--------------");
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(1,"java入坑",1,new BigDecimal(1),new BigDecimal(1)));
        cart.addItem(new CartItem(2,"java入坑222",1,new BigDecimal(2),new BigDecimal(2)));
        System.out.println(cart);
        System.out.println("--------------");
        cart.updateItem(1,4);
        System.out.println(cart);
    }
}