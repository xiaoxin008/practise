package com.xiaoxin008.spring.aop.targetsource;

import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.OtherBean;
import com.xiaoxin008.spring.aop.auto.MyMethodInterceptor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.IntStream;

/**
 * 自定义TargetSource功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CustomerTS implements TargetSource {

    private int count = 0;

    @Override
    public Class<?> getTargetClass() {
        return OtherBean.class;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() throws Exception {
        count++;
        return new OtherBean();
    }

    @Override
    public void releaseTarget(Object target) throws Exception {

    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
        CustomerTS customerTS = new CustomerTS();
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setSingleton(false);
        proxyFactoryBean.setTargetSource(customerTS);
        proxyFactoryBean.addAdvice(new MyMethodInterceptor());
        IOther other = (IOther)proxyFactoryBean.getObject();
        other.doOther();
        IOther other1 = (IOther)proxyFactoryBean.getObject();
        other1.doOther();
        int count = customerTS.getCount();
        System.out.println(other);
        System.out.println(other1);
        System.out.println(count);
    }
}
