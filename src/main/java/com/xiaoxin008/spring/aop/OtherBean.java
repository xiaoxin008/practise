package com.xiaoxin008.spring.aop;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class OtherBean implements IOther {

    private Integer count = 0;

    @Override
    public void doCount() {
        count++;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
