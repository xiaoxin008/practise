package com.xiaoxin008.spring.ioc.bean.common;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商店实体 依赖水果实体
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class Shop {

    @Autowired
    private Peach peach;

    @Autowired
    private Apple apple;

    private String name = "Full-Mart";

    private ObjectFactory peachFactory;

    public Shop() {
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Peach getPeach() {
        return (Peach) peachFactory.getObject();
    }

    public void setPeach(Peach peach) {
        this.peach = peach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeachFactory(ObjectFactory peachFactory) {
        this.peachFactory = peachFactory;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "peach=" + peach +
                ", apple=" + apple +
                ", name='" + name + '\'' +
                '}';
    }

    //xml文件
//    <bean id="shop" class="com.xiaoxin008.spring.ioc.bean.common.Shop">
//        <property name="peachFactory" ref="peachFactory"></property>
//    </bean>
//
//    <bean id="peachFactory" class="org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean">
//        <property name="targetBeanName">
//            <!--  bean 的标志符名称  -->
//            <idref bean="peach"/>
//        </property>
//    </bean>
//
//    <bean id="peach" class="com.xiaoxin008.spring.ioc.bean.common.Peach" scope="prototype">
//        <property name="color" value="green"></property>
//        <property name="shape" value="small"></property>
//        <property name="origin" value="tianjin"></property>
//    </bean>
}
