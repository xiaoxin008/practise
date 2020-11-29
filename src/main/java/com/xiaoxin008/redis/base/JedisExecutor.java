package com.xiaoxin008.redis.base;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.function.Function;

/**
 * Jedis
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class JedisExecutor {

     private Jedis jedis;

     {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        //最大连接数
        jedisPoolConfig.setMaxTotal(1000);
        //获取连接时的最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(300000);
        jedis = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null, 0).getResource();
    }

    public <T>T execute(Function<Jedis,T> function){
        try {
            return function.apply(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    public static void main(String[] args) {
        JedisExecutor jedisExecutor = new JedisExecutor();
        String wfx = jedisExecutor.execute((j) -> j.get("wfx"));
        System.out.println(wfx);
    }
}
