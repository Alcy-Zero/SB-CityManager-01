package com.hand.demo.api.filter_interceptor;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("TestFilter1初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("############TestFilter1 doFilter before###########");
        filterChain.doFilter(servletRequest, servletResponse);   //必须执行，否则前端请求不会进入
        System.out.println("############TestFilter1 doFilter after############");
    }

    @Override
    public void destroy() {
        System.out.println("TestFilter1销毁。。。");
    }
}