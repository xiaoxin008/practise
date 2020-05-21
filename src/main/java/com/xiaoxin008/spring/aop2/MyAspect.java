package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * 使用@AspectJ规范完成AOP操作
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(public void *.method*())") //pointcut expression
    public void myPointcut(){} //pointcut signature

    @Around("myPointcut()")
    public Object myAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("动态代理执行过程1！");
        return joinPoint.proceed();
    }

    public static void main(String[] args) {
        //手动装配
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(new MyMethod());
        proxyFactory.addAspect(MyAspect.class);
        MyMethod proxy = (MyMethod)proxyFactory.getProxy();
        proxy.method1();

        //自动装配
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        myMethod.method1();
        myMethod.method2();
    }
}
