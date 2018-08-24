package com.hand.demo.api.controller.v0;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Controller
@RequestMapping("/v0")
public class RequestResponseController {

    @GetMapping("/test1")
    @ResponseBody
    public String test1(HttpServletRequest request, HttpServletResponse response) {
        //设置响应头信息
        response.addHeader("key-01", "value-01");
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        //返回响应头信息
        StringBuilder sb = new StringBuilder();
        sb.append(response.toString() + "<br>");
        sb.append(response.getHeaderNames() + "<br>");
        sb.append(response.getStatus() + "<br>");
        return sb.toString();
    }

    @GetMapping("/test2")
    @ResponseBody
    public String index(HttpServletRequest request) {
        //获取请求头信息
        StringBuilder sb = new StringBuilder();
        sb.append("<br>请求头信息：<br>");
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getRequestURI() + "<br>");
        sb.append(request.getRequestURL() + "<br>");
        //获取请求消息头
        sb.append("<br>请求消息头：<br>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {            //读取请求消息头
            String name = headerNames.nextElement();
            sb.append(name + "----:----" + request.getHeader(name) + "<br>");
        }
        //获取cookies
        sb.append("<br>cookies信息：<br>");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                sb.append("Cookie: " + cookie.getName() + " value: " + cookie.getValue()+"<br>");
            }
        }
        return sb.toString();
    }

    @GetMapping("/test3")
    @ResponseBody
    public String test3(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append(response.getHeaderNames() + "<br>");
        sb.append(response.getStatus() + "<br>");         //状态响应码
        //设置响应头信息
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", 0);
            //告知客户端不缓存
        response.addCookie(new Cookie("name-01", "value-01"));
        response.addCookie(new Cookie("name-02", "value-02"));
            //添加Cookie
        sb.append(response.getHeaderNames() + "<br>");
            //查看此时Header信息
        return sb.toString();
    }

    //重定向
    @GetMapping("/test4")
    public String test4(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(500);  //将状态响应码设置成500
        return "redirect:/v0/test3";
    }   //会被过滤两次

    //转发
    @GetMapping("/test5")
    public String test5(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(666);  //将状态响应码设置成500
        return "forward:/v0/test3";
    }   //只会被过滤一次
}
