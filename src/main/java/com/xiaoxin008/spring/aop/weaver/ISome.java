package com.xiaoxin008.spring.aop.weaver;

public interface ISome {

    default void doSome(){
        System.out.println("do something.....");
    }

    default void doPlay(){
        System.out.println("do playing.....");
    }
}
