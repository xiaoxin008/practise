package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;

/**
 * 使用@AspectJ规范完成AOP操作,@Before注解
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class MyAspectBefore {

    @Pointcut("execution(public void *.method*(..)) && args(obj,name,age) && @annotation(d)") //pointcut expression
    public void myPointcut(String obj,String name,String age,Deprecated d){} //pointcut signature

    @Before("myPointcut(obj,name,age,d)")
    public void myAdvice(JoinPoint joinPoint, String obj, String name, String age,Deprecated d) throws Throwable{
        System.out.println("动态代理前置处理执行过程！");
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        Class<? extends Annotation> aClass = d.annotationType();
        System.out.println(aClass);
        System.out.println(obj+"_"+name+"_"+age);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        myMethod.method3("obj","Jane","13");
    }
}
