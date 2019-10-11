package com.xiaoxin008.jvmexecute.gc;

/**
 * 查看GC日志
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class GCLog {

    public static void main(String[] args) {
        GCLog gcLog = new GCLog();
        gcLog = null;
        System.gc();
    }
}
