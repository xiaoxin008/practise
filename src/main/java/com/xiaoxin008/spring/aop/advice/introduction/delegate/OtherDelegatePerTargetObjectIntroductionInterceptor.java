package com.xiaoxin008.spring.aop.advice.introduction.delegate;

import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.OtherBean;
import com.xiaoxin008.spring.aop.SomeBean;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatePerTargetObjectIntroductionInterceptor;

/**
 * 使用DelegateIntroductionInterceptor实现引入
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class OtherDelegatePerTargetObjectIntroductionInterceptor {

    public static void main(String[] args) {
        SomeBean someBean = new SomeBean();
        ProxyFactory proxyFactory = new ProxyFactory(someBean);
        proxyFactory.addAdvice(new DelegatePerTargetObjectIntroductionInterceptor(OtherBean.class, IOther.class));
        IOther proxy1 = (IOther)proxyFactory.getProxy();
        proxy1.doOther();
        proxyFactory.setTarget(new SomeBean());
        IOther proxy2 = (IOther)proxyFactory.getProxy();
        proxy2.doOther();
        proxyFactory.setTarget(someBean);
        IOther proxy3 = (IOther)proxyFactory.getProxy();
        proxy3.doOther();

//        结果
//        com.xiaoxin008.spring.aop.advice.introduction.OtherBean@5387f9e0
//        do other something...
//        com.xiaoxin008.spring.aop.advice.introduction.OtherBean@2cdf8d8a
//        do other something...
//        com.xiaoxin008.spring.aop.advice.introduction.OtherBean@5387f9e0
//        do other something...
//        说明：每一个somebean对象都有一个独立的otherbean对象对应

    }
}
