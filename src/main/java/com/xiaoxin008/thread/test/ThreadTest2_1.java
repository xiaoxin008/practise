package com.xiaoxin008.thread.test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测试2：线程一 与 线程二 交替输出 数字和字母
 *
 * 解法1：同步
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest2_1 {

    private final static List<Integer> nums = IntStream.rangeClosed(1, 26).boxed().collect(Collectors.toList());
    private final static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static Object lock = new Object();

    public static void main(String[] args) {
        Thread lt = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.println(nums.get(i));
                    lock.notify();
                    try {
                        if (i != 25) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("数字线程--结束");
        });

        Thread nt = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.println(letters.charAt(i));
                    lock.notify();
                    try {
                        if (i != 25) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("字母线程--结束");
        });

        lt.start();
        nt.start();
    }

}
