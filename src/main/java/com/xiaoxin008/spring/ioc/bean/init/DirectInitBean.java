package com.xiaoxin008.spring.ioc.bean.init;

import com.xiaoxin008.spring.ioc.bean.common.Apple;
import com.xiaoxin008.spring.ioc.bean.common.Peach;
import com.xiaoxin008.spring.ioc.bean.common.Shop;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 通过直接编码方式 通过BeanFactory初始化Bean
 * BeanFactory接口定义了获取Bean的方法，而BeanDefinition接口定义抽象了Bean的注册逻辑
 * 每一个受管的对象，在容器中都会有一个BeanDefinition实例与之相对应
 * BeanDefinition的实例负责保存对象的所有必要信息，包括class类型，是否是抽象类，构造方法参数以及其他属性
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DirectInitBean {

    public static void main(String[] args) {
        //DefaultListableBeanFactory同时实现了BeanFactory接口和BeanDefinitionRegistry接口（间接实现BeanDefinition接口）
        //构造一个DefaultListableBeanFactory作为BeanDefinition-Registry，然后将其交给bindViaCode方法
        //进行具体的对象注册和相关依赖管理，然后通过bindViaCode返回BeanFactory取得需要的对象
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanFactory beanFactory = bindViaCode(factory);
        Shop shop = (Shop)beanFactory.getBean("shop");
        System.out.println(shop.toString());
    }

    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        //首先针对相应的业务对象构造与其相对应的BeanDefinition
        //使用了RootBeanDefinition作为BeanDefinition的实现类
        AbstractBeanDefinition shopBean = new RootBeanDefinition(Shop.class);
        AbstractBeanDefinition appleBean = new RootBeanDefinition(Apple.class);
        AbstractBeanDefinition peachBean = new RootBeanDefinition(Peach.class);

        //构造完成后，将这些BeanDefinition注册到通过方法参数传进来的BeanDefinitionRegistry中
        registry.registerBeanDefinition("shop",shopBean);
        registry.registerBeanDefinition("apple",appleBean);
        registry.registerBeanDefinition("peach",peachBean);

        MutablePropertyValues peachPropertyValues = new MutablePropertyValues();
        peachPropertyValues.addPropertyValue(new PropertyValue("color","green"));
        peachPropertyValues.addPropertyValue(new PropertyValue("shape","big"));
        peachPropertyValues.addPropertyValue(new PropertyValue("origin","shandong"));
        peachBean.setPropertyValues(peachPropertyValues);

        MutablePropertyValues applePropertyValues = new MutablePropertyValues();
        applePropertyValues.addPropertyValue(new PropertyValue("color","red"));
        applePropertyValues.addPropertyValue(new PropertyValue("shape","small"));
        applePropertyValues.addPropertyValue(new PropertyValue("origin","hebei"));
        appleBean.setPropertyValues(applePropertyValues);

        //通过setter方式注入
//        MutablePropertyValues shopPropertyValues = new MutablePropertyValues();
//        shopPropertyValues.addPropertyValue(new PropertyValue("apple",appleBean));
//        shopPropertyValues.addPropertyValue(new PropertyValue("peach",peachBean));
//        shopPropertyValues.addPropertyValue(new PropertyValue("name","Full-Mart"));
//        shopBean.setPropertyValues(shopPropertyValues);

        //通过构造器方式注入相关依赖
        ConstructorArgumentValues constructorValues = new ConstructorArgumentValues();
        constructorValues.addIndexedArgumentValue(0,appleBean);
        constructorValues.addIndexedArgumentValue(1,peachBean);
        constructorValues.addIndexedArgumentValue(2,"JLF");
        shopBean.setConstructorArgumentValues(constructorValues);
        //最后，以BeanFactory的形式返回已经注册并绑定了所有相关业务对象的BeanDefinitionRegistry
        return (BeanFactory)registry;
    }
}