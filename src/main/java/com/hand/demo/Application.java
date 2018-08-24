package com.hand.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


// Spring Boot 应用的标识
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.hand.demo.infra.mapper")
//@tk.mybatis.spring.annotation.MapperScan(basePackages = { "com.hand.demo.mapper" },markerInterface = CityMapper.class)
@ServletComponentScan
public class Application {

    protected static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        logger.info("SpringBoot开始加载");

        SpringApplication.run(Application.class, args);

        logger.error("SpringBoot加载完毕");
    }

}
