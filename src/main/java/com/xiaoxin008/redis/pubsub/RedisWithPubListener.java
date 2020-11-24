package com.xiaoxin008.redis.pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * 监听器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithPubListener extends JedisPubSub {

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        RedisWithPubSub.flags.add(subscribedChannels);
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(channel + "=" + message);
    }
}
