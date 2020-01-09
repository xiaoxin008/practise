package com.xiaoxin008.spring.processor.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 自定义BeanPostProcessor
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class PromotionPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if(o instanceof Promotion){
            ((Promotion) o).sale();
        }
        return o;
    }

    interface Promotion {
        void sale();
    }

    static class ShopPromotion implements Promotion {
        @Override
        public void sale() {
            System.out.println("商店减价拉！！！！");
        }
    }

    public static void main(String[] args) {
        //BeanFactory
//        ConfigurableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("application-context.xml"));
//        beanFactory.addBeanPostProcessor(new PromotionPostProcessor());
//        System.out.println(beanFactory.getBean("shopPromotion"));
//        xml配置文件
//        <bean id="shopPromotion" class="com.xiaoxin008.spring.processor.bean.PromotionPostProcessor$ShopPromotion"></bean>

        //Application
          ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
          System.out.println(context.getBean("shopPromotion"));
//        xml配置文件
//        <bean id="shopPromotion" class="com.xiaoxin008.spring.processor.bean.PromotionPostProcessor$ShopPromotion"></bean>
//        <bean id="promotionPostProcessor" class="com.xiaoxin008.spring.processor.bean.PromotionPostProcessor"></bean>
    }
}
