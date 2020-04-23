package com.xiaoxin008.spring.aop.targetsource;

import com.xiaoxin008.spring.aop.IOther;
import org.springframework.aop.target.CommonsPoolTargetSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.IntStream;

/**
 * 测试CommonsPoolTargetSource功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CommonsPoolTS {

    public static void main(String[] args) {

//    <bean id="otherBean" class="com.xiaoxin008.spring.aop.OtherBean" scope="prototype"></bean>
//
//    <bean id="commonsPool" class="org.springframework.aop.target.CommonsPoolTargetSource">
//        <property name="targetBeanName" value="otherBean"></property>
//        <property name="maxSize" value="2"></property>
//    </bean>
//
//    <bean id="commonsPoolProxy" class="org.springframework.aop.framework.ProxyFactoryBean">
//        <property name="targetSource" ref="commonsPool"></property>
//        <property name="singleton" value="false"></property>
//        <property name="interceptorNames">
//            <list>
//                <value>myInterceptor</value>
//            </list>
//        </property>
//    </bean>
//
//    <bean id="myInterceptor" class="com.xiaoxin008.spring.aop.auto.MyMethodInterceptor"></bean>

        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-target-source.xml");
        CommonsPoolTargetSource commonsPool = (CommonsPoolTargetSource)cp.getBean("commonsPool");
        int activeCount = commonsPool.getActiveCount();
        System.out.println(activeCount);
        IntStream.rangeClosed(0,100).forEach(i -> {
            Thread thread = new Thread(() -> {
                IOther commonsPoolProxy = (IOther)cp.getBean("commonsPoolProxy");
                commonsPoolProxy.doOther();
                System.out.println(commonsPoolProxy);
            });
            thread.start();
        });

//        结果：在池中只存在两个对象
//        com.xiaoxin008.spring.aop.OtherBean@6bf473fb
//        动态代理逻辑
//        动态代理逻辑
//        do other something...
//        com.xiaoxin008.spring.aop.OtherBean@2c734ad1
//                动态代理逻辑
//        com.xiaoxin008.spring.aop.OtherBean@6bf473fb
//                动态代理逻辑
//        com.xiaoxin008.spring.aop.OtherBean@2c734ad1

    }

}
