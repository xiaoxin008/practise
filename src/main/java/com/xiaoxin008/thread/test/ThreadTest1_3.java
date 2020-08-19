package com.xiaoxin008.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 测试1：线程一不断往集合中添加数字直到10，线程二在集合长度为5时输出集合长度
 * <p>
 * 解法3：CountDownLatch
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest1_3 {

    private static List list = new ArrayList<>();

    private static void add(int element) {
        list.add(element);
    }

    private static int size() {
        return list.size();
    }

    private static CountDownLatch c1 = new CountDownLatch(1);
    private static CountDownLatch c2 = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                add(i);
                System.out.println(i);
                if(size() == 5){
                    c2.countDown();
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("集合中的元素个数为：%d",size()));
            c1.countDown();
        });

        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

    }

}
