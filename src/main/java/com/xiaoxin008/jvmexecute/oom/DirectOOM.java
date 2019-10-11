package com.xiaoxin008.jvmexecute.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存-OOM异常
 * VM Arg：-XX:MaxDirectMemorySize=10M
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DirectOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe)field.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
