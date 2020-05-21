package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 观察各Advice的执行顺序
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class OrderAspect {

    @Pointcut("execution(public void *.method*())")
    public void myPointcut(){}

    @Before("myPointcut()")
    public void beforeAdvice3(){
        System.out.println("@Before1动态代理执行过程！");
    }

    @Before("myPointcut()")
    public void beforeAdvice2(){
        System.out.println("@Before2动态代理执行过程！");
    }

    @Around("myPointcut()")
    public Object beforeAdvice4(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("@Around动态代理执行过程！前");
        Object proceed = joinPoint.proceed();
        System.out.println("@Around动态代理执行过程！后");
        return proceed;
    }

    @After("myPointcut()")
    public void afterAdvice2(){
        System.out.println("@After1动态代理执行过程！");
    }

    @After("myPointcut()")
    public void afterAdvice1(){
        System.out.println("@After2动态代理执行过程！");
    }

    @AfterReturning("myPointcut()")
    public void afterReturningAdvice2(){
        System.out.println("@AfterReturning1动态代理执行过程！");
    }

    @AfterReturning("myPointcut()")
    public void afterReturningAdvice1(){
        System.out.println("@AfterReturning2动态代理执行过程！");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        myMethod.method1();

//        @Around动态代理执行过程！
//        @Before动态代理执行过程！
//        method1 execute!
//        @After动态代理执行过程！
//        @AfterReturning动态代理执行过程！
    }
}
