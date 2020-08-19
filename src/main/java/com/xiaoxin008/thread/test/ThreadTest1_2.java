package com.xiaoxin008.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试1：线程一不断往集合中添加数字直到10，线程二在集合长度为5时输出集合长度
 * <p>
 * 解法2：wait notify
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest1_2 {

    private static List list = new ArrayList<>();

    private static void add(int element) {
        list.add(element);
    }

    private static int size() {
        return list.size();
    }

    private static Object lock = new Object();

    public  static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    add(i);
                    System.out.println(i);
                    if (size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("集合里的元素数量为：%d",size()));
                lock.notify();
            }
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
