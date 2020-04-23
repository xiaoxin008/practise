package com.xiaoxin008.spring.aop.weaver;

import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.ISome;
import com.xiaoxin008.spring.aop.SomeBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context-proxy-factorybean.xml");
        ISome some = (ISome) context.getBean("proxyBean1");
        some.doSome();

        SomeBean some2 = (SomeBean)context.getBean("proxyBean2");
        IOther other1 = (IOther)context.getBean("proxyBean2");
        IOther other2 = (IOther)context.getBean("proxyBean2");

        some2.doSome();
        other1.doCount();
        other1.doCount();
        other2.doCount();
        System.out.println(other1.getCount());
        System.out.println(other2.getCount());

    }

}
