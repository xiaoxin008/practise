package com.xiaoxin008.jvmexecute.gc;

/**
 * 测试大对象直接放入老年代内存区
 * VM Args: -Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class TestPretenureSizeThreshold {

    private static int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] pretenureSizeThreshold;
        pretenureSizeThreshold = new byte[4*_1M];
    }
}
