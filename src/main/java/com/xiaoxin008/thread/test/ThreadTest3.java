package com.xiaoxin008.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * 验证线程间的不可见性
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest3 {

    private static boolean flag = false;
    private static int count = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            while (!flag){
//            这里面千万不能使用System.out.println 因为println()方法里面加了锁，会刷新主内存数据，让其出现能够可见的效果
//            System.out.println("22222222222");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        count = 42;
    }

}
