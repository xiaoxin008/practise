package com.xiaoxin008.spring.aop.weaver;

import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.ISome;
import com.xiaoxin008.spring.aop.OtherBean;
import com.xiaoxin008.spring.aop.SomeBean;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * ProxyFactory的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ProxyFactoryIIUsage {

    public static void main(String[] args) {
        //1.创建proxyFactory对象
        ProxyFactory proxyFactory = new ProxyFactory();
        //2.设置（被代理）目标对象
        proxyFactory.setTarget(new SomeBean());
        //3.设置目标对象接口和新织入的接口类型
        proxyFactory.setInterfaces(new Class[]{ISome.class,IOther.class});
        //4.创建Advice
        MethodInterceptor methodInterceptor = (invoker) -> {
            if(invoker.getMethod().getDeclaringClass().isAssignableFrom(ISome.class)){
                return invoker.proceed();
            }else{
                return invoker.getMethod().invoke(new OtherBean(),invoker.getArguments());
            }
        };
        //5.创建Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(methodInterceptor);
        //6.设置Order
        advisor.setOrder(0);
        //7.添加Advisor
        proxyFactory.addAdvisor(advisor);
        Object proxy = proxyFactory.getProxy();
        ISome some = (ISome) proxy;
        some.doSome();
        IOther other = (IOther) proxy;
        other.doOther();
        System.out.println(proxy.getClass());

//        结果
//        do something.....
//        do other something...
//        class com.sun.proxy.$Proxy0 说明是基于接口的代理
    }

}
