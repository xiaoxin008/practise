package com.xiaoxin008.spring.aop2;

public interface Father {

    default void baby(){
        System.out.println("照看小孩");
    }
}
