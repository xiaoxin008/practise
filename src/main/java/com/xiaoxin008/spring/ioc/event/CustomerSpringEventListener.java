package com.xiaoxin008.spring.ioc.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 测试spring内置事件
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class CustomerSpringEventListener implements ApplicationListener<ContextClosedEvent>{

    private ApplicationEventPublisher publisher;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("容器关闭了");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.close();
    }
}
