package com.xiaoxin008.jvmexecute.loader.initialization;

/**
 * 测试
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class InitializationTest {

    public static void main(String[] args) {
        //1、子类引用父类的静态变量 不会触发子类初始化过程
        System.out.println(SubClass.value);
        //2、创建目标数组对象不会触发目标类初始化过程
        SubClass[] subClass = new SubClass[1];
        //3、引用目标类中的常量不会触发目标类的初始化过程
        System.out.println(SubClass.subValue);
    }
}
