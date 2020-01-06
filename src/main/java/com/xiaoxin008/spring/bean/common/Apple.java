package com.xiaoxin008.spring.bean.common;

import org.springframework.stereotype.Component;

/**
 * 苹果实体
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class Apple {

    private String color = "red";

    private String shape = "big";

    private String origin = "tianjin";

    public Apple() {
    }

    public Apple(String color, String shape, String origin) {
        this.color = color;
        this.shape = shape;
        this.origin = origin;
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
