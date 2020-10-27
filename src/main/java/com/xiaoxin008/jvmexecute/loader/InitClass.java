package com.xiaoxin008.jvmexecute.loader;

/**
 * 初始化
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class InitClass {

    public static int i = 4;

    public static void main(String[] args) {
        i = 4;
        System.out.println(InitClass.i);
    }
}
