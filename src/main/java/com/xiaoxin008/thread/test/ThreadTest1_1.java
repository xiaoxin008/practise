package com.xiaoxin008.thread.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试1：线程一不断往集合中添加数字直到10，线程二在集合长度为5时输出集合长度
 *
 * 解法1：start join
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest1_1 {

    private static List list = new ArrayList<>();

    private static void add(int element){
        list.add(element);
    }

    private static int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Thread t2 = new Thread(() -> {
            System.out.println("t2开始");
            System.out.println("t2结束");

        });

        Thread t1 = new Thread(() -> {
            System.out.println("t1开始");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                add(i);
                if(size() == 5){
                    try {
                        t2.start();
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1结束");
        });
        t1.start();
    }

}
