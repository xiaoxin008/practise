package com.xiaoxin008.redis.lock;

import com.xiaoxin008.redis.base.JedisContainer;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的分布式锁
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Data
public class RedisWithReentrantLock {

    private final String lockName = "lock";
    private final String SUCCESS = "OK";

    private static int count = 5000;

    @NonNull
    private Jedis jedis;

    public void lock(String flag) {
        while (true) {
            String result = jedis.set(lockName, flag, new SetParams().nx().ex(5));
            if (SUCCESS.equals(result)) {
//                System.out.println("线程" + flag + "加锁成功！");
                return;
            } else {
                try {
                    int delay = (int) (Math.random() * 1000);
//                    System.out.println("线程" + flag + "获取锁失败，休眠" + delay + "ms！");
                    TimeUnit.MILLISECONDS.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unlock(String flag) {
        if (flag.equals(jedis.get(lockName))) {
            jedis.del(lockName);
//            System.out.println("线程" + flag + "解锁！");
        }
    }

    public static void work(String username) {
        if (count == 0) {
            System.out.println("亲爱的" + username + "此次发售结束！请下次参与！");
        } else {
            count--;
            System.out.println(username + "获得了小熊dunk!----" + "当前库存--------------------------" + count);
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (RedisWithReentrantLock.class) {
                count += 10000;
                System.out.println("补货10000双！-------------------------------------------------------------------------------------");
            }
        }).start();
        for (int i = 0; i < 1000000; i++) {
            Thread thread = new Thread(() -> {
                RedisWithReentrantLock lock = new RedisWithReentrantLock(JedisContainer.getJedis());
                lock.lock(Thread.currentThread().getName());
                work(Thread.currentThread().getName());
                lock.unlock(Thread.currentThread().getName());
                lock.getJedis().close();
            });
            thread.start();
        }
    }
}
