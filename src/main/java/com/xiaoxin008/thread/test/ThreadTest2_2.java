package com.xiaoxin008.thread.test;

import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测试2：线程一 与 线程二 交替输出 数字和字母
 *
 * 解法2：LockSupport
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest2_2 {

    private final static List<Integer> nums = IntStream.rangeClosed(1, 26).boxed().collect(Collectors.toList());
    private final static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Thread lt,nt;

    public static void main(String[] args) {
        nt = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.println(nums.get(i));
                LockSupport.unpark(lt);
                if(i != 25) {
                    LockSupport.park();
                }
            }
        });

        lt = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.println(letters.charAt(i));
                LockSupport.unpark(nt);
                if(i != 25){
                    LockSupport.park();
                }
            }
        });

        lt.start();
        nt.start();

    }

}
