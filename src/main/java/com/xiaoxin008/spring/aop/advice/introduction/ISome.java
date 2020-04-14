package com.xiaoxin008.spring.aop.advice.introduction;

public interface ISome {

    default void doSome(){
        System.out.println("do something.....");
    }
}
