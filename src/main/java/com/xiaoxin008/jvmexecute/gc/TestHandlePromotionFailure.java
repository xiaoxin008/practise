package com.xiaoxin008.jvmexecute.gc;

/**
 * HandlePromotionFailure参数机制
 * VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class TestHandlePromotionFailure {

    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,
        allocation6,allocation7,allocation8;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null; //第一次Minor GC 老年代空间>当前存活对象大小  进行 Minor GC
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation8 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null; //第二次Minor GC 老年代空间<当前存活对象大小 但 老年代空间>历代晋升到老年代对象的平均大小
                            //进行 Minor GC，但存活对象总大小大于老年代剩余空间，所以此次Minor GC发生(promotion failed),这时需要手动进行Full GC
        System.gc();
        allocation7 = new byte[2 * _1MB];
    }
}
