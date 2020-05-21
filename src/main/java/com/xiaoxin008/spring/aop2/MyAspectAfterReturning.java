package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * 使用@AspectJ规范完成AOP操作,@AfterReturning注解
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class MyAspectAfterReturning {

    @Pointcut("execution(public String *.method*(..)) && args(obj,name,age)") //pointcut expression
    public void myPointcut(String obj,String name,String age){} //pointcut signature

    @AfterReturning(pointcut = "myPointcut(obj,name,age)" ,returning = "result")
    public void myAdvice(JoinPoint joinPoint,String obj,String name,String age,String result){
        System.out.println("后置动态代理执行过程！");
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        System.out.println("返回结果为："+result);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        myMethod.method4("me","Jane","13");
    }
}
