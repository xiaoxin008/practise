package com.xiaoxin008.spring.aop2;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class Man implements Father{
    @Override
    public void baby() {
        System.out.println("喜当爹！");
    }
}
