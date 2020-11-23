package com.xiaoxin008.redis.transation;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Redis事务
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithTransation {

    private static Jedis jedis = JedisContainer.getJedis();

    public static void main(String[] args) {
        jedis.set("account","100");
        jedis.close();
//        出现并发问题
//        for (int i = 0; i < 1000; i++) {
//            new Thread(() -> {
//                Jedis jedis = JedisContainer.getJedis();
//                jedis.set("account",""+(Integer.valueOf(jedis.get("account")) + 1));
//                jedis.close();
//            }).start();
//        }

        //使用乐观锁
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                List<Object> exec = null;
                Jedis jc = null;
                while (exec == null) {
                    jc = JedisContainer.getJedis();
                    jc.watch("account");
                    int account = Integer.valueOf(jc.get("account")) + 1;
                    Transaction multi = jc.multi();
                    multi.set("account",""+account);
                    exec = multi.exec();
                    multi.close();
                }
                jc.close();
            }).start();
        }



//        Pipeline pipelined = jedis.pipelined();
//        pipelined.multi();
//        pipelined.set("wfx","222");
//        pipelined.set("wfx","333");
////        pipelined.discard();
//        pipelined.exec();
//        pipelined.sync();
    }
}
