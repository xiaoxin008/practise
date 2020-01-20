package com.xiaoxin008.spring.ioc.bean.common;

import org.springframework.stereotype.Component;

/**
 * 桃子实体
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class Peach {

    private String color;

    private String shape;

    private String origin;

    public Peach() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
