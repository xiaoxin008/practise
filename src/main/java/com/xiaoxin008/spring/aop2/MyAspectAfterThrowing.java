package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * 使用@AspectJ规范完成AOP操作,@AfterThrowing注解
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class MyAspectAfterThrowing {

    @Pointcut("execution(public void *.method*(..))") //pointcut expression
    public void myPointcut(){} //pointcut signature

    @AfterThrowing(pointcut = "myPointcut()",throwing = "e")
    public void myAdvice(JoinPoint joinPoint,NullPointerException e){
        System.out.println("错误动态代理执行过程！");
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        String message = e.getMessage();
        System.out.println("报错啦！"+message);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        MyMethod myMethod = (MyMethod)cp.getBean("myMethod");
        try {
            myMethod.method3(null,"Jane","13");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
