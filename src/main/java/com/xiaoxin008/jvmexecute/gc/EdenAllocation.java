package com.xiaoxin008.jvmexecute.gc;

/**
 * 测试新对象优先分配在Eden区
 * VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class EdenAllocation {

    public static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
         allocation1 = new byte[2*_1MB];
         allocation2 = new byte[2*_1MB];
         allocation3 = new byte[2*_1MB]; //这里发生一次Minor GC
         allocation4 = new byte[4*_1MB];
    }
}
