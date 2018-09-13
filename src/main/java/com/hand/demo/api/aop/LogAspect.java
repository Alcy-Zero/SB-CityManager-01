package com.hand.demo.api.aop;

import com.alibaba.fastjson.JSONObject;
import com.hand.demo.Application;
import com.hand.demo.api.controller.v1.CityController;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    long ss1 = 0;
    long ss2 = 0;

    //定义切入点
    @Pointcut("execution(public * com.hand.demo.api.controller.v1.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求内容
        //logger.info("-----Before开始-----");
        logger.info("---请求内容：");
        logger.info("     URL : " + request.getRequestURL().toString());
        logger.info("     HTTP_METHOD : " + request.getMethod());
        logger.info("     IP : " + request.getRemoteAddr());
        logger.info("     CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("     ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //logger.info("-----Before结束-----");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    //    除了@Around外，参数JoinPoint可选，
    //    JoinPoint里包含了类名、被切面的方法名，参数等属性，可供读取使用。
    //    @Around参数必须为ProceedingJoinPoint。
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) {
        //logger.info("——————Around开始——————");
        Object result = null;
        ss1 = System.currentTimeMillis();
        try {
            //pjp.proceed相应于执行被切面的方法。
            result = pjp.proceed();
            //logger.info("——————Around执行了被切方法——————" +
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        //logger.info("++++++++++方法最后执行: ++++++++++");
        CityController cc = (CityController) joinPoint.getTarget();
        ss2 = System.currentTimeMillis();
        logger.info("==总响应时间：" + (ss2 - ss1) + " ms");
        logger.info("====发送请求的响应时间：" + (cc.getS2()-cc.getS1()) + " ms");
        logger.info("====记录日志的花费时间：" + ((ss2 - ss1)-(cc.getS2()-cc.getS1())) + " ms");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    //@AfterReturning方法里，returning = “XXX”，XXX即为在controller里方法的返回值
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        //logger.info("==========方法的返回值:==========" +
        //        "       " + ret);

    }



    //后置异常通知
    @AfterThrowing(pointcut = "webLog()")
    //@AfterThrowing里，可加throwing ="XXX"，供读取异常信息
    public void doAfterThrowing(JoinPoint joinPoint) {
        logger.info("X--------方法异常时执行:--------X");
    }

}
