package com.xiaoxin008.spring.aop.weaver;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

import java.util.concurrent.TimeUnit;

/**
 * 基于类的代理
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class PointcutPerformanceAdvice implements MethodInterceptor {

    static class MockTask {

        public void execute() {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mockTask executed!!!");
        }
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long l = System.currentTimeMillis();
        System.out.println("表演开始");
        methodInvocation.proceed();
        System.out.println("表演结束");
        return (System.currentTimeMillis() - l);
    }

    public static void main(String[] args) {
        ProxyFactory weaver = new ProxyFactory();
        //这里不可以使用匿名内部类，因为匿名内部类是final修饰的（相当于闭包）
        weaver.setTarget(new MockTask());
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PointcutPerformanceAdvice());
        weaver.addAdvisor(advisor);
        MockTask proxy = (MockTask) weaver.getProxy();
        proxy.execute();
    }
}
