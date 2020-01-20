package com.xiaoxin008.spring.ioc.event.container;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class MethodExecutionListener implements ApplicationListener<MethodExecutionEvent> {

    @Override
    public void onApplicationEvent(MethodExecutionEvent event) {
        if(MethodExecutionEvent.EVENT_BEGIN.equals(event.getMethodStatus())){
            System.out.println("start execute method["+event.getMethodName()+"]");
        }else{
            System.out.println("finish execute method["+event.getMethodName()+"]");
        }
    }
}
