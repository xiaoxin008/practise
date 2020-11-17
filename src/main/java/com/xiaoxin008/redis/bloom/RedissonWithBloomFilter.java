package com.xiaoxin008.redis.bloom;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 通过Redisson使用布隆过滤器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedissonWithBloomFilter {

    public static void main(String[] args) {
        bloomFilter();
    }

    private static void bloomFilter(){
        int count = 0;
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("bloomFilter");
        bloomFilter.tryInit(10000L, 0.03);
        for (int i = 7000; i < 9000; i++) {
            boolean contains = bloomFilter.contains("user"+i);
            System.out.println(contains);
            if(!contains){
                bloomFilter.add("user"+i);
            }else{
                count++;
            }
        }
        System.out.println(count);
        redisson.shutdown();
    }
}
