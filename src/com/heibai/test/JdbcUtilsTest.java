package com.heibai.test;

import org.junit.Test;

public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        /**
         * 因为使用了ThreadLocal
         */
        /*for (int i = 0; i < 100; i++){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }*/
    }

}
