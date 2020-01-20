package com.xiaoxin008.spring.ioc.processor.container;

import com.xiaoxin008.spring.ioc.bean.common.Datasource;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 手动装配BeanFactoryPostProcessor
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ManualInitProcessor {

    public static void main(String[] args) {
        ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("application-context.xml"));
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("datasource.properties"));
        propertyPlaceholderConfigurer.postProcessBeanFactory(beanFactory);
        Datasource datasource = (Datasource)beanFactory.getBean("datasource");
        System.out.println(datasource);
    }

}
