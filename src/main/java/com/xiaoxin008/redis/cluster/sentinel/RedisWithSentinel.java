package com.xiaoxin008.redis.cluster.sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Redis主从同步 —— 哨兵
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithSentinel {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        Set<String> sentinels = new HashSet<>(Arrays.asList("192.168.91.132:26379","192.168.91.132:26380"));
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster",sentinels);
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set("wfx2","123");
        String s = jedis.get("wfx");
        System.out.println(s);
        jedis.close();
    }
}
