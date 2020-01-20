package com.xiaoxin008.spring.ioc.bean.init;

import com.xiaoxin008.spring.ioc.bean.common.Shop;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 通过xml方式初始化Bean
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class InitXmlBean {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanFactory beanFactory = bindViaCode(factory);
        Shop shop = (Shop)beanFactory.getBean("shop");
        System.out.println(shop.getPeach());
        Shop shop1 = (Shop)beanFactory.getBean("shop");
        System.out.println(shop1.getPeach());
        Shop shop2 = (Shop)beanFactory.getBean("shop");
        System.out.println(shop2.getPeach());
    }

    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:application-context.xml");
        return (BeanFactory)registry;
    }

}
