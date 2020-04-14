package com.xiaoxin008.spring.aop.weaver;

public interface IOther {

    default void doOther(){
        System.out.println("do other something...");
    }
}
