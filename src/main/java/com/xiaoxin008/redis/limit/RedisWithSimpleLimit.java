package com.xiaoxin008.redis.limit;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.Jedis;

/**
 * 使用Redis做简单限流
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithSimpleLimit {

    private static Jedis jedis = JedisContainer.getJedis();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            boolean b = actionIsAllowed("wfx", "use", 5, 5);
            System.out.println(b);
        }
        jedis.close();
    }

    /**
     * period秒中之内不能访问超过stipulate次
     * 会有并发问题
     *
     * @param userId
     * @param actionKey
     * @param period
     * @param stipulate
     * @return
     */
    public static boolean actionIsAllowed(String userId, String actionKey, int period, int stipulate) {
        String key = String.format("history:%s:%s", userId, actionKey);
        long currentTimeMillis = System.currentTimeMillis();
        jedis.zremrangeByScore(key, 0, currentTimeMillis - 1000 * period);
        Long zcard = jedis.zcard(key);
        boolean access = zcard.intValue() < stipulate;
        if (access) {
            jedis.zadd(key, currentTimeMillis, currentTimeMillis + "");
        }
        jedis.expire(key,period+1);
        return access;
    }
}
