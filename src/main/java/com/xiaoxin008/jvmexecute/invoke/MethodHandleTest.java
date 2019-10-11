package com.xiaoxin008.jvmexecute.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Method Handle基础用法展示
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MethodHandleTest {

    public static class ClassA{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable{
        Object obj = System.currentTimeMillis() % 2 == 0?System.out:new ClassA();
        getPrintlnMH(obj).invokeExact("xxxxx");
    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable{
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(reveiver.getClass(),"println",methodType).bindTo(reveiver);
    }
}
