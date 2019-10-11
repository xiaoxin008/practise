package com.xiaoxin008.jvmexecute.gc;

/**
 * JVM会把长期存活的对象放入老年代(动态)
 * VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * -XX:TargetSurvivorRatio=90 -XX:+PrintTenuringDistribution -XX:MaxTenuringThreshold=15
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class TestAge2 {

    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4*_1MB];
        allocation4 = new byte[4*_1MB];
        allocation4 = null;
        allocation3 = new byte[4*_1MB];
    }
}
