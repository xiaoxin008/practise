package com.xiaoxin008.spring.aop.targetsource;

import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.OtherBean;
import com.xiaoxin008.spring.aop.auto.MyMethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.HotSwappableTargetSource;

/**
 * 测试HotSwappableTargetSource功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class HotSwappableTS {

    public static void main(String[] args) {
        OtherBean otherBean = new OtherBean();
        HotSwappableTargetSource hotSwappableTargetSource = new HotSwappableTargetSource(otherBean);
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetSource(hotSwappableTargetSource);
        proxyFactoryBean.addAdvice(myMethodInterceptor);
        IOther obj = (IOther)proxyFactoryBean.getObject();
        obj.doOther();
        hotSwappableTargetSource.swap(new IOther() {
            @Override
            public void doOther() {
                System.out.println("做其他的事情！");
            }

            @Override
            public void doCount() {

            }

            @Override
            public Integer getCount() {
                return null;
            }
        });
        IOther obj1 = (IOther)proxyFactoryBean.getObject();
        obj1.doOther();
    }
}
