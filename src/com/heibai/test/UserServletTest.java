package com.heibai.test;

import java.lang.reflect.Method;

/**
 * @author heibai
 * @date 2020/6/27 14:57
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login的方法");
    }
    public void register(){
        System.out.println("这是register的方法");
    }
    public void updateUsername(){
        System.out.println("这是updateUsername的方法");
    }

    public static void main(String[] args) {
        String action="register";
        try {
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethod);
            declaredMethod.invoke(new UserServletTest());
           // declaredMethod.invoke()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
