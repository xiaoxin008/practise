package com.xiaoxin008.spring.aop.targetsource;

import com.xiaoxin008.spring.aop.IOther;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试PrototypeTargetSource功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class PrototypeTS {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-target-source.xml");
        IOther proxyPrototype = (IOther)cp.getBean("proxyPrototype");
        proxyPrototype.doOther();
        IOther proxyPrototype1 = (IOther)cp.getBean("proxyPrototype");
        proxyPrototype1.doOther();
        System.out.println(proxyPrototype == proxyPrototype1);
    }

}
