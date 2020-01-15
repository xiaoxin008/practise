package com.xiaoxin008.spring.event.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 自定义事件
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MethodExecutionEvent extends ApplicationContextEvent {

    public static final String EVENT_BEGIN = "begin";
    public static final String EVENT_END = "end";

    private String methodName;

    private String methodStatus;

    public MethodExecutionEvent(ApplicationContext source) {
        super(source);
    }

    public MethodExecutionEvent(ApplicationContext source, String methodName, String methodStatus) {
        super(source);
        this.methodName = methodName;
        this.methodStatus = methodStatus;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodStatus() {
        return methodStatus;
    }

    public void setMethodStatus(String methodStatus) {
        this.methodStatus = methodStatus;
    }
}
