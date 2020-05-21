package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
@Order(1)
public class MyAspect2 {

    @Pointcut("execution(public void *.method*())") //pointcut expression
    public void myPointcut(){} //pointcut signature

    @Around("myPointcut()")
    public Object myAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("动态代理执行过程2！");
        return joinPoint.proceed();
    }

    public static void main(String[] args) {
        //手动方式
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setProxyTargetClass(true);
        factory.setTarget(new MyMethod());
        factory.addAspect(MyAspect2.class);
        factory.addAspect(MyAspect.class);
        MyMethod proxy = (MyMethod)factory.getProxy();
        proxy.method1();

    }
}
