package com.xiaoxin008.redis.pipe;

import com.xiaoxin008.redis.base.RedissonContainer;
import org.redisson.api.RedissonClient;

/**
 * Redis使用管道
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithPipe {

    private static RedissonClient redissonClient = RedissonContainer.getRedissonClient();

    public static void main(String[] args) throws Exception{
        //不使用管道2248
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            RBucket<Object> bucket = redissonClient.getBucket("key" + i);
//            bucket.set(i);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
//        redissonClient.shutdown();

        //使用管道-289ms
//        long start = System.currentTimeMillis();
//        RBatch batch = redissonClient.createBatch();
//        for (int i = 0; i < 10000; i++) {
//            RBucketAsync<Object> bucket = batch.getBucket("key" + i);
//            bucket.setAsync(i);
//        }
//        BatchResult<?> execute = batch.execute();
//        long end = System.currentTimeMillis();
//        System.out.println(execute.getResponses());
//        redissonClient.shutdown();
//        System.out.println(end - start);
    }
}
