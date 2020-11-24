package com.xiaoxin008.redis.pubsub;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * PubSub
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithPubSub {

    public static CopyOnWriteArrayList<Integer> flags = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Jedis jedis = JedisContainer.getJedis();
                jedis.subscribe(new RedisWithPubListener(), "yh");
            }).start();
        }

        new Thread(() -> {
            while (true) {
                if (flags.size() == 10L) {
                    Jedis jedis = JedisContainer.getJedis();
                    jedis.publish("yh", "测试1");
                    jedis.publish("yh", "测试2");
                    jedis.publish("yh", "测试3");
                    return;
                }
            }
        }).start();
    }
}
