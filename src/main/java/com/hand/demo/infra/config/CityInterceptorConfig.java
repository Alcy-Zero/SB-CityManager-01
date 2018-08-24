package com.hand.demo.infra.config;

import com.hand.demo.api.filter_interceptor.CityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册拦截器
 */
@SpringBootConfiguration
public class CityInterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CityInterceptor cityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径
        //单*拦不到，双**拦截其下所有路径
        registry.addInterceptor(cityInterceptor).addPathPatterns("/v2/**");
    }
}