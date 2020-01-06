package com.xiaoxin008.spring.processor;

import com.xiaoxin008.spring.bean.common.Datasource;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 自动装配BeanFactoryPostProcessor
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class AutoInitProcessor {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Datasource datasource = (Datasource)context.getBean("datasource");
        System.out.println(datasource);

        //xml配置文件
//      <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
//          <property name="locations" value="datasource.properties"></property>
//      </bean>
    }

}
