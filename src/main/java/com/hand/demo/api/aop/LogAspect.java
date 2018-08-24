package com.hand.demo.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    //定义切入点
    @Pointcut("execution(public * com.hand.demo.api.controller.v1.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求内容
        System.out.println("-----Before开始-----");
        System.out.println("        URL : " + request.getRequestURL().toString());
        System.out.println("        HTTP_METHOD : " + request.getMethod());
        System.out.println("        IP : " + request.getRemoteAddr());
        System.out.println("        CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("        ARGS : " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("-----Before结束-----");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        System.out.println("++++++++++方法最后执行: ++++++++++");
        System.out.println("        joinPoint.toString(): ");
        System.out.println("        "+joinPoint);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    //@AfterReturning方法里，returning = “XXX”，XXX即为在controller里方法的返回值
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        System.out.println("==========方法的返回值:==========\n" +
                "       " + ret);

    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    //    除了@Around外，参数JoinPoint可选，
    //    JoinPoint里包含了类名、被切面的方法名，参数等属性，可供读取使用。
    //    @Around参数必须为ProceedingJoinPoint。
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("——————方法环绕start——————");
        try {
            //pjp.proceed相应于执行被切面的方法。
            Object o = pjp.proceed();
            System.out.println("——————方法环绕proceed——————\n" +
                    "       结果是:" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    //后置异常通知
    @AfterThrowing(pointcut = "webLog()")
    //@AfterThrowing里，可加throwing ="XXX"，供读取异常信息
    public void throwss(JoinPoint joinPoint) {
        System.out.println("X--------方法异常时执行:--------X");
        System.out.println("        joinPoint.toString(): ");
        System.out.println("        "+joinPoint);
    }


}
