package com.xiaoxin008.spring.aop2;

import java.util.UUID;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class Teacher implements Woker{

    private String code = UUID.randomUUID().toString();

    @Override
    public void work() {
        System.out.println("教书育人！");
    }

    @Override
    public String getCode() {
        return code;
    }
}
