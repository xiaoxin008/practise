package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * 使用@AspectJ规范完成AOP操作,@After注解
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class MyAspectAfter {

    @Pointcut("execution(public void *.method*())") //pointcut expression
    public void myPointcut(){} //pointcut signature

    @After(value = "myPointcut()")
    public void myAdvice(){
        System.out.println("后置动态代理执行过程！");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        myMethod.method1();
    }
}
