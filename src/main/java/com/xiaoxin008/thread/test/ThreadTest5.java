package com.xiaoxin008.thread.test;

/**
 * 缓存行对齐
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest5 {

    private static class Padding{
        private volatile long l1,l2,l3,l4,l5,l6,l7 = 0L;
    }

    //这里是否继承Padding类对性能有很大影响-缓存行对齐优化
    private static class T extends Padding{
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception{
        long l = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                arr[0].x = i;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                arr[1].x = i;
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }

}
