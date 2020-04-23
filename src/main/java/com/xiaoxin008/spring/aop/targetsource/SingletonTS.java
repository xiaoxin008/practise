package com.xiaoxin008.spring.aop.targetsource;

import com.xiaoxin008.spring.aop.IOther;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试SingletonTargetSource功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class SingletonTS {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-target-source.xml");
        IOther proxySingleton = (IOther)cp.getBean("proxySingleton");
        proxySingleton.doOther();
        IOther proxySingleton1 = (IOther)cp.getBean("proxySingleton");
        proxySingleton1.doOther();
        System.out.println(proxySingleton.equals(proxySingleton1));
    }

}
