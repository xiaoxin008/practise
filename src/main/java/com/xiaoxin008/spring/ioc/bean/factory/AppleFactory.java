package com.xiaoxin008.spring.ioc.bean.factory;

import com.xiaoxin008.spring.ioc.bean.common.Apple;
import org.springframework.beans.factory.FactoryBean;

/**
 * 苹果工厂
 *
 * FactoryBean类型的bean定义，通过正常的id引用，容器返回的是FactoryBean所“生产”的对象类型，而非FactoryBean实现本身
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class AppleFactory implements FactoryBean<Apple> {

    @Override
    public Apple getObject() throws Exception {
        System.out.println("苹果工厂包装！");
        return new Apple("green","middle","富士苹果工厂");
    }

    @Override
    public Class<?> getObjectType() {
        return Apple.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


//    xml配置文件
//    <bean id="shop" class="com.xiaoxin008.spring.ioc.bean.common.Shop" scope="prototype">
//        <property name="name" value="Full-Mart"></property>
//        <property name="apple" ref="appleFactory"></property>
//    </bean>
//
//    <bean id="appleFactory" class="com.xiaoxin008.spring.ioc.bean.factory.AppleFactory">
//
//    </bean>

}
