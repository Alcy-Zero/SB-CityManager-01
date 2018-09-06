package com.hand.demo.api.filter_interceptor;

import com.hand.demo.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
public class TestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("==TestFilter1初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("进行了一次拦截");
        System.out.println("############TestFilter1 doFilter before###########");
        filterChain.doFilter(servletRequest, servletResponse);   //必须执行，否则前端请求不会进入
        System.out.println("############TestFilter1 doFilter after############");
    }

    @Override
    public void destroy() {
        logger.info("==TestFilter1销毁。。。");
    }
}