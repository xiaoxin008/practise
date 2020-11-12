package com.xiaoxin008.redis.queue;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 基于Redis的延迟队列
 * <p>
 * 需求：
 * 1、1000个用户抢购10个限量鞋
 * 2、抢到之后提示用户付款
 * 5次提示后，还未付款，则订单取消，付款后，提示付款成功，订单生成
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Data
@RequiredArgsConstructor
public class RedisWithDelayQueue {

    @NonNull
    private Jedis jedis;

    @NonNull
    private String queueName;

    public Long addQueue(String obj, Long delay) {
        Long zadd = jedis.zadd(this.queueName, delay, obj);
        return zadd;
    }

    public Long length() {
        return jedis.zcard(this.queueName);
    }

    public String getElement() {
        String element = null;
        Set<String> elementSet = jedis.zrange(this.queueName, 0, 1);
        if (elementSet.size() > 0) {
            element = elementSet.iterator().next();
            element = jedis.zrem(queueName, element) > 0 ? element : null;
        }
        return element;
    }

    public String delayElement(long time) {
        String element = null;
        Set<String> elementSet = jedis.zrangeByScore(this.queueName, 0, time);
        if (elementSet.size() > 0) {
            element = elementSet.iterator().next();
            element = jedis.zrem(queueName, element) > 0 ? element : null;
        }
        return element;
    }
}
