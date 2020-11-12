package com.xiaoxin008.redis.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 购买者
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Dunker implements Serializable {

    private String id;
    private String phone;
    private Boolean isPaid;
    private Integer tryTime;
    private Integer order;
}
