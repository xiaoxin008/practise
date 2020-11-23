package com.xiaoxin008.redis.base;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedissonContainer {

    private static Config config;

    static {
        config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    }

    public static RedissonClient getRedissonClient(){
        return Redisson.create(config);
    }
}
