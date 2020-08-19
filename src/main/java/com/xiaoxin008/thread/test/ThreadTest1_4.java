package com.xiaoxin008.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 测试1：线程一不断往集合中添加数字直到10，线程二在集合长度为5时输出集合长度
 * <p>
 * 解法4：LockSupport
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest1_4 {

    private static List list = new ArrayList<>();

    private static void add(int element) {
        list.add(element);
    }

    private static int size() {
        return list.size();
    }

    private static Thread t1,t2;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                add(i);
                System.out.println(i);
                if(size() == 5){
                   LockSupport.unpark(t2);
                   LockSupport.park();
                }
            }
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println(String.format("集合中的元素个数为：%d",size()));
            LockSupport.unpark(t1);
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
