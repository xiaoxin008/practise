package com.xiaoxin008.spring.event.container;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 自定义发布器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class MethodExecutionPublisher implements ApplicationContextAware {

    private ApplicationContext publisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.publisher = applicationContext;
    }

    public void methodToMonitor() throws InterruptedException{
        MethodExecutionEvent beginEvent = new MethodExecutionEvent(publisher, "methodToMonitor", MethodExecutionEvent.EVENT_BEGIN);
        publisher.publishEvent(beginEvent);
        TimeUnit.SECONDS.sleep(3);
        MethodExecutionEvent endEvent = new MethodExecutionEvent(publisher, "methodToMonitor", MethodExecutionEvent.EVENT_END);
        publisher.publishEvent(endEvent);
    }

    public static void main(String[] args) throws InterruptedException{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        MethodExecutionPublisher publisher = context.getBean(MethodExecutionPublisher.class);
        publisher.methodToMonitor();
    }
}
