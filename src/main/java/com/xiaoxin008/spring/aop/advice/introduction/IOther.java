package com.xiaoxin008.spring.aop.advice.introduction;

public interface IOther {

    default void doOther(){
        System.out.println(this);
        System.out.println("do other something...");
    }
}
