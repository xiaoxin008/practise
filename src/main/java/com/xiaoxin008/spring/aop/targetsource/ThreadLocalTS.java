package com.xiaoxin008.spring.aop.targetsource;

import com.xiaoxin008.spring.aop.IOther;
import org.springframework.aop.target.CommonsPoolTargetSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.IntStream;

/**
 * 测试ThreadLocalTargetSource功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadLocalTS {

    public static void main(String[] args) {

//    <bean id="otherBean" class="com.xiaoxin008.spring.aop.OtherBean" scope="prototype"></bean>
//
//    <bean id="threadLocalTargetSource" class="org.springframework.aop.target.ThreadLocalTargetSource">
//        <property name="targetBeanName" value="otherBean"></property>
//    </bean>
//
//    <bean id="threadLocalProxy" class="org.springframework.aop.framework.ProxyFactoryBean">
//        <property name="targetSource" ref="threadLocalTargetSource"></property>
//        <property name="interceptorNames">
//            <list>
//                <value>myInterceptor</value>
//            </list>
//        </property>
//    </bean>
//
//    <bean id="myInterceptor" class="com.xiaoxin008.spring.aop.auto.MyMethodInterceptor"></bean>

        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-target-source.xml");
        IntStream.rangeClosed(0,100).forEach(i -> {
            Thread thread = new Thread(() -> {
                IOther threadLocalProxy = (IOther)cp.getBean("threadLocalProxy");
                threadLocalProxy.doOther();
                System.out.println(threadLocalProxy);
            });
            thread.start();
        });

//        结果：每一个线程的代理对象都是独立的
//        com.xiaoxin008.spring.aop.OtherBean@2cfbb6ff
//        动态代理逻辑
//        do other something...
//        动态代理逻辑
//        com.xiaoxin008.spring.aop.OtherBean@17801e5a
//        动态代理逻辑
//        do other something...
//        动态代理逻辑
//        com.xiaoxin008.spring.aop.OtherBean@5acc6953
//        动态代理逻辑
//        do other something...

    }

}
