package com.xiaoxin008.jvmexecute.loader.initialization;

/**
 * 父类
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class SuperClass {

    static {
        System.out.println("SuperClass is Initialization! ");
    }

    protected static int value = 123;
}
