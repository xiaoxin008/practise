package com.xiaoxin008.spring.aop.weaver;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * Advice
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MyProxyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("动态代理执行过程！！");
        return methodInvocation.proceed();
    }
}
