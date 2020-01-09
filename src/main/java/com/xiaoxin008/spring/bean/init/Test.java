package com.xiaoxin008.spring.bean.init;

import com.xiaoxin008.spring.resource.ResourceCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 测试
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:application-context.xml");
        ResourceCollector resourceCollector = (ResourceCollector) applicationContext.getBean("resourceCollector");
        System.out.println(resourceCollector.getResource().exists());
    }

}
