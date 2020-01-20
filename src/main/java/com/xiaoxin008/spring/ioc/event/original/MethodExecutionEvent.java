package com.xiaoxin008.spring.ioc.event.original;

import java.util.EventObject;

/**
 * 自定义事件
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MethodExecutionEvent extends EventObject {

    public static final String EVENT_BEGIN = "begin";
    public static final String EVENT_END = "end";

    private String methodName;

    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source, String methodName) {
        super(source);
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
