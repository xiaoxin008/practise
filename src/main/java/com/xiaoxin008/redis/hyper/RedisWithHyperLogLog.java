package com.xiaoxin008.redis.hyper;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.Jedis;

/**
 * 使用Redis HyperLogLog数据结构
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithHyperLogLog {

    public static void main(String[] args) {
//        deduplicationStatistics();
        merge();
    }

    /**
     * HyperLogLog可以去重但是不是很精确 适用于数据量较大的页面访问统计
     * 测试一百万条数据误差：1001656 1%左右
     */
    private static void deduplicationStatistics(){
        Jedis jedis = JedisContainer.getJedis();
        for (int i = 0; i < 1000000; i++) {
            Long pfadd = jedis.pfadd("UV", "user"+i);
            System.out.println(pfadd);
        }
        long uv = jedis.pfcount("UV");
        System.out.println(uv);
        jedis.close();
    }

    /**
     * HyperLogLog支持数据去重然后合并
     *
     */
    private static void merge(){
        Jedis jedis = JedisContainer.getJedis();
        for (int i = 0; i < 500000; i++) {
            Long pfadd = jedis.pfadd("UV2", "user"+i);
            System.out.println(pfadd);
        }
        jedis.pfmerge("UV","UV2");
        long uv = jedis.pfcount("UV");
        System.out.println(uv);
        jedis.close();
    }
}
