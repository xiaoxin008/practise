package com.xiaoxin008.redis.zip;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 对象压缩
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithZip {

    private static Jedis jedis = JedisContainer.getJedis();

    public static void main(String[] args) {
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 512; i++) {
            pipelined.sadd("wfx",i+"");
        }
        pipelined.sync();
        String s = jedis.objectEncoding("wfx");
        pipelined.close();
        //intset
        System.out.println(s);
        jedis.sadd("wfx","512");
        //hashtable
        String s1 = jedis.objectEncoding("wfx");
        System.out.println(s1);
        jedis.close();
    }
}
