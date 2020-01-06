package com.xiaoxin008.spring.bean.init;

import com.xiaoxin008.spring.bean.common.Shop;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用注解初始化对象
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class InitAnnotationBean {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Shop shop = (Shop)context.getBean("shop");
        System.out.println(shop);
    }

}
