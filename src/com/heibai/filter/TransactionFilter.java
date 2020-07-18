package com.heibai.filter;

import com.heibai.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author heibai
 * @date 2020/7/3 14:12
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {

    }
}
