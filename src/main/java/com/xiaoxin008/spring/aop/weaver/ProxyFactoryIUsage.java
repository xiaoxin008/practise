package com.xiaoxin008.spring.aop.weaver;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * ProxyFactory的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ProxyFactoryIUsage {

    public static void main(String[] args) {
        //1.创建proxyFactory对象
        ProxyFactory proxyFactory = new ProxyFactory();
        //2.设置（被代理）目标对象
        proxyFactory.setTarget(new SomeBean());
        //2.（针对于接口的动态代理）可省略，因为默认会检测Target实现的接口
        proxyFactory.setInterfaces(new Class[]{ISome.class});
        //3.创建Advisor(Aspect)
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        //4.设置Advisor的Pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("doSome");
        advisor.setPointcut(pointcut);
        MethodInterceptor methodInterceptor = (invoker) -> {
            System.out.println("动态代理附加属性！");
            return invoker.proceed();
        };
        //5.设置Advisor的Advice
        advisor.setAdvice(methodInterceptor);
        //6.设置Order（执行顺序）
        advisor.setOrder(0);
        //7.给ProxyFactory添加Aspect
        proxyFactory.addAdvisor(advisor);
        //8.获取代理对象（因为使用的是动态代理，代理的是接口，无法转换为目标实体对象）
        ISome proxy = (ISome)proxyFactory.getProxy();
        proxy.doSome();
        proxy.doPlay();
        System.out.println(proxy.getClass());

//        结果
//        动态代理附加属性！
//        do something.....
//        do playing.....
//        class com.sun.proxy.$Proxy0 说明是基于接口的代理

    }

}
