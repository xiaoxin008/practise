package com.xiaoxin008.spring.ioc.bean.scope;

import com.xiaoxin008.spring.ioc.bean.common.Shop;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 自定义bean的作用域(每两次重新创建对象)
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CustomizeScope implements Scope {

    //定义一个对象计数器
    private final Map<String,Integer> objectCount = new HashMap();

    //定义一个对象集合
    private final Map<String,Object> objectMap = new HashMap();

    //每几次更换新对象
    private final int frequency = 2;

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        return Optional.ofNullable(objectMap.get(s)).map(o -> {
            Object object;
            Integer count = objectCount.get(s);
            if(count > 0){
                object = objectMap.get(s);
                objectCount.replace(s,objectCount.get(s) - 1);
            }else{
                object = objectFactory.getObject();
                objectCount.put(s,frequency - 1);
                objectMap.put(s,object);
            }
            return object;
        }).orElseGet(() -> {
            Object object = objectFactory.getObject();
            objectCount.put(s,frequency - 1);
            objectMap.put(s,object);
            return object;
        });
    }

    @Override
    public Object remove(String s) {
        objectCount.remove(s);
        return objectMap.remove(s);
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    public static void main(String[] args) {
        DefaultListableBeanFactory registry = new DefaultListableBeanFactory();
        registry.registerScope("customize",new CustomizeScope());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:application-context.xml");
        Shop shop = (Shop)registry.getBean("shop");
        System.out.println(shop.toString());
        Shop shop1 = (Shop)registry.getBean("shop");
        System.out.println(shop1.toString());
        Shop shop2 = (Shop)registry.getBean("shop");
        System.out.println(shop2.toString());
        Shop shop3 = (Shop)registry.getBean("shop");
        System.out.println(shop3.toString());
        Shop shop4 = (Shop)registry.getBean("shop");
        System.out.println(shop4.toString());
    }

//    xml文件
//    <bean id="shop" class="com.xiaoxin008.spring.ioc.bean.common.Shop" autowire="byType" scope="customize">
//        <property name="name" value="Full-Mart"></property>
//    </bean>

}
