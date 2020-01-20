package com.xiaoxin008.spring.aop.weaver;

public interface ITest {
    default void test(){
        System.out.println("测试....");
    }
}
