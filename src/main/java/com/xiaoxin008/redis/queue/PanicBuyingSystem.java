package com.xiaoxin008.redis.queue;

import com.alibaba.fastjson.JSON;
import com.xiaoxin008.redis.base.JedisContainer;
import com.xiaoxin008.redis.lock.RedisWithReentrantLock;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * 抢购限量鞋
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class PanicBuyingSystem {

    private static int dunks = 10;

    private static RedisWithDelayQueue winningQueue;
    private static RedisWithDelayQueue noticeQueue;

    private static void snapUp(String json) {
        Dunker dunker = JSON.parseObject(json, Dunker.class);
        if (dunks > 0) {
            dunks--;
            //抢到的用户，放入付款延迟队列
            Long addQueue = winningQueue.addQueue(json, (long) dunks);
            if (addQueue == 1L) {
                System.out.println(dunker.getId() + "抢购到了小熊dunk!,剩余货量--------" + dunks);
            }
        } else {
            System.out.println(dunker.getId() + "很遗憾，您未抢到该鞋款！----------------");
        }
    }

    public static void main(String[] args) {
        winningQueue = new RedisWithDelayQueue(JedisContainer.getJedis(), "winner");
        noticeQueue = new RedisWithDelayQueue(JedisContainer.getJedis(), "notice");

        for (int i = 0; i < 10000; i++) {
            int order = i;
            Thread thread = new Thread(() -> {
                Dunker dunker = new Dunker(UUID.randomUUID().toString(), "13398110001", false, 5, order);
                Jedis jedis = JedisContainer.getJedis();
                RedisWithReentrantLock lock = new RedisWithReentrantLock(jedis);
                lock.lock(dunker.getId());
                snapUp(JSON.toJSONString(dunker));
                lock.unlock(dunker.getId());
                jedis.close();
            });
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (winningQueue.length() > 0) {
            String element = winningQueue.getElement();
            if (element != null) {
                Dunker dunker = JSON.parseObject(element, Dunker.class);
                System.out.println("恭喜ID为" + dunker.getId() + "获得了限量版dunk购买权限------小熊dunk!!!");
                if(2 == dunker.getOrder()){
                    dunker.setIsPaid(true);
                    noticeQueue.addQueue(JSON.toJSONString(dunker), System.currentTimeMillis() + 50);
                }else{
                    noticeQueue.addQueue(element, System.currentTimeMillis() + 5000);
                }
            }
        }

        while (noticeQueue.length() > 0) {
            String noticeElement = noticeQueue.delayElement(System.currentTimeMillis());
            if (noticeElement != null) {
                Dunker dunker = JSON.parseObject(noticeElement, Dunker.class);
                if (!dunker.getIsPaid()) {
                    if (dunker.getTryTime() > 0) {
                        dunker.setTryTime(dunker.getTryTime() - 1);
                        Long notice = noticeQueue.addQueue(JSON.toJSONString(dunker), System.currentTimeMillis() + 2000);
                        if (notice > 0) {
                            System.out.println("ID为" + dunker.getId() + "的用户请尽快付款！！！！！！！");
                        }
                    } else {
                        System.out.println("ID为" + dunker.getId() + "的用户，因你没有按时付款，你的订单已取消！");
                    }
                }else{
                    System.out.println("ID为" + dunker.getId() + "的用户，小熊duck—————GOT IT！");
                }
            }
        }
    }
}
