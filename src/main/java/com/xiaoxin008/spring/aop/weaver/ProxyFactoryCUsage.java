package com.xiaoxin008.spring.aop.weaver;

import com.xiaoxin008.spring.aop.SomeBean;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * ProxyFactory的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ProxyFactoryCUsage {

    public static void main(String[] args) {
        //1.创建proxyFactory对象
        ProxyFactory proxyFactory = new ProxyFactory();
        //2.设置（被代理）目标对象
        proxyFactory.setTarget(new SomeBean());
        //3.针对于类的动态代理，此项配置不可省略
        proxyFactory.setOptimize(true);
        //4.创建Advisor(Aspect)
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        //5.设置Advisor的Pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("doSome");
        advisor.setPointcut(pointcut);
        MethodInterceptor methodInterceptor = (invoker) -> {
            System.out.println("动态代理附加属性！");
            return invoker.proceed();
        };
        //6.设置Advisor的Advice
        advisor.setAdvice(methodInterceptor);
        //7.设置Order（执行顺序）
        advisor.setOrder(0);
        //8.给ProxyFactory添加Aspect
        proxyFactory.addAdvisor(advisor);
        //9.获取代理对象（因为使用的是动态代理，代理的是接口，无法转换为目标实体对象）
        SomeBean proxy = (SomeBean)proxyFactory.getProxy();
        proxy.doSome();
        proxy.doPlay();
        System.out.println(proxy.getClass());

//        结果
//        动态代理附加属性！
//        do something.....
//        do playing.....
//        class com.xiaoxin008.spring.aop.SomeBean$$EnhancerBySpringCGLIB$$7f395981 说明是基于类的代理

    }

}
