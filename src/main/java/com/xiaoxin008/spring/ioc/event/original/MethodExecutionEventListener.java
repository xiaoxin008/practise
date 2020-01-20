package com.xiaoxin008.spring.ioc.event.original;

import java.util.EventListener;

/**
 * 自定义事件监听器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public interface MethodExecutionEventListener extends EventListener {

    default void onMethodBegin(MethodExecutionEvent event){
        String methodName = event.getMethodName();
        System.out.println("start to execute the method[' "+methodName+"']");
    }

    default void onMethodEnd(MethodExecutionEvent event){
        String methodName = event.getMethodName();
        System.out.println("finished to execute the method[' "+methodName+"']");
    }

}
