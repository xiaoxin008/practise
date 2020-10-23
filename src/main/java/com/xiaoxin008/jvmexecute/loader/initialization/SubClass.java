package com.xiaoxin008.jvmexecute.loader.initialization;

/**
 * 子类
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class SubClass extends SuperClass{

    public static final int subValue = 345;

    static {
        System.out.println("SubClass is Initialization!");
    }
}
