package com.xiaoxin008.spring.aop2;

/**
 * 工人
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public interface Woker {

    default void work(){
        System.out.println("努力工作！！！！");
    };

    String getCode();
}
