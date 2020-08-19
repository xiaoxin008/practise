package com.xiaoxin008.thread.test;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测试2：线程一 与 线程二 交替输出 数字和字母
 *
 * 解法3：ReentrantLock和Condition
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest2_3 {

    private final static List<Integer> nums = IntStream.rangeClosed(1, 26).boxed().collect(Collectors.toList());
    private final static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        Condition lc = lock.newCondition();
        Condition nc = lock.newCondition();

        Thread nt = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.println(nums.get(i));
                    lc.signal();
                    if (i != 25) {
                        nc.await();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });


        Thread lt = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.println(letters.charAt(i));
                    nc.signal();
                    if (i != 25) {
                        lc.await();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        lt.start();
        nt.start();
    }

}
