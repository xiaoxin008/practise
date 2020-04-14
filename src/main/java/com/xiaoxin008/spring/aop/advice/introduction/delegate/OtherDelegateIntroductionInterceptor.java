package com.xiaoxin008.spring.aop.advice.introduction.delegate;

import com.xiaoxin008.spring.aop.advice.introduction.IOther;
import com.xiaoxin008.spring.aop.advice.introduction.ISome;
import com.xiaoxin008.spring.aop.advice.introduction.OtherBean;
import com.xiaoxin008.spring.aop.advice.introduction.SomeBean;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * 使用DelegateIntroductionInterceptor实现引入
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class OtherDelegateIntroductionInterceptor {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new SomeBean());
        proxyFactory.addAdvice(new DelegatingIntroductionInterceptor(new OtherBean()));
        ISome sp = (ISome) proxyFactory.getProxy();
        sp.doSome();
        IOther op = (IOther) proxyFactory.getProxy();
        op.doOther();
        proxyFactory.setTarget(new SomeBean());
        IOther op1 = (IOther) proxyFactory.getProxy();
        op1.doOther();

//        结果
//        do something.....
//        com.xiaoxin008.spring.aop.advice.introduction.OtherBean@4b9af9a9
//        do other something...
//        com.xiaoxin008.spring.aop.advice.introduction.OtherBean@4b9af9a9
//        do other something...
//        说明otherbean对象被所有somebean对象所共有
    }
}
