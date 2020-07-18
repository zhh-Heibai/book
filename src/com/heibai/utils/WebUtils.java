package com.heibai.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author heibai
 * @date 2020/6/27 15:53
 */
public class WebUtils {
    public static<T> T copyParamToBean(Map value, T bean){
//        System.out.println("注入之前:"+bean);
        try {
            BeanUtils.populate(bean,value);
//            System.out.println("注入之后："+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
