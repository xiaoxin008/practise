package com.xiaoxin008.spring.jdbc.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 汽车类
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Data
public class Car {

    private Long id;

    private String name;

    private String brand;

    private int volume;

    private int busload;

    private String sn;

    private int family;

    private BigDecimal price;
}
