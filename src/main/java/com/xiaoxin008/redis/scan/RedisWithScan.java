package com.xiaoxin008.redis.scan;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;

/**
 * Scan指令找出符合条件的key
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithScan {

    private static Jedis jedis = JedisContainer.getJedis();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            jedis.set("key"+i,i+"");
        }
        jedis.close();
    }

    public static List<String> scan(String pattern, int count) {

        ScanResult<String> scan = jedis.scan("0", new ScanParams().match(pattern).count(count));
        List<String> result = scan.getResult();
        while (!"0".equals(scan.getCursor())) {
            scan = jedis.scan(scan.getCursor(), new ScanParams().match("key9*").count(1000));
            result.addAll(scan.getResult());
        }
        return result;
    }
}
