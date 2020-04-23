package com.xiaoxin008.spring.aop.auto;

import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.ISome;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试BeanNameAutoProxyCreator功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class BeanNameAutoProxy {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-beanName-auto.xml");
        ISome someBean = (ISome)cp.getBean("someBean");
        IOther otherBean = (IOther)cp.getBean("otherBean");
        someBean.doSome();
        otherBean.doOther();
    }

}
