package com.xiaoxin008.spring.aop.advice.introduction;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class OtherBean implements IOther{

    private int number = 1;

    public OtherBean() {
    }

    public OtherBean(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
