package com.xiaoxin008.spring.bean.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商店实体 依赖水果实体
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class Shop {

    @Autowired
    private Peach peach;

    @Autowired
    private Apple apple;

    private String name = "Full-Mart";


    public Shop() {
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Peach getPeach() {
        return peach;
    }

    public void setPeach(Peach peach) {
        this.peach = peach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "peach=" + peach +
                ", apple=" + apple +
                ", name='" + name + '\'' +
                '}';
    }
}
