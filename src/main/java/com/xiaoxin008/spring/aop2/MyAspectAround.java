package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * 使用@AspectJ规范完成AOP操作
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class MyAspectAround {

    @Pointcut("execution(public String *.method*(..)) && args(obj,name,age)") //pointcut expression
    public void myPointcut(String obj,String name,String age){} //pointcut signature

    @Around("myPointcut(obj,name,age)")
    public String myAdvice(ProceedingJoinPoint joinPoint,String obj,String name,String age) throws Throwable{
        System.out.println("动态代理执行过程！");
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        return (String) joinPoint.proceed(new Object[]{"you","Rose","22"});
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        String s = myMethod.method4("me", "Jack", "11");
        System.out.println(s);
    }
}
