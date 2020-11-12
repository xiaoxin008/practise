package com.xiaoxin008.redis.base;

import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 获取Jedis容器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Data
public class JedisContainer {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        //最大连接数
        jedisPoolConfig.setMaxTotal(1000);
        //获取连接时的最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(300000);
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null, 0);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
