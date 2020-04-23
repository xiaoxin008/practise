package com.xiaoxin008.spring.aop;

public interface IOther {

    default void doOther(){
        System.out.println("do other something...");
    }

    default void doCount(){
        System.out.println("do counting...");
    }

    default Integer getCount(){
        return 0;
    }
}
