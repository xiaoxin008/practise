package com.xiaoxin008.jvmexecute.invoke;

import java.lang.invoke.*;

/**
 * invokedynamic指令演示
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class InvokeDynamicTest {

    public static void main(String[] args) throws Throwable{
        //执行入口
        INDY_BootstrapMethod().invokeExact("test");
    }

    public static void testMethod(String s){
        System.out.println(" hello String: "+ s);
    }

    //通过传入的参数设定好动态调用点
    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable{
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class,name,mt));
    }

    //通过方法描述向BootstrapMethod方法传入参数类型
    private static MethodType MT_BootstrapMethod(){
        return MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;" +
                        "Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",null);
    }

    //调用 BootstrapMethod 返回 MethodHandle
    private static MethodHandle MH_BootstrapMethod() throws Throwable{
        return MethodHandles.lookup().findStatic(InvokeDynamicTest.class,"BootstrapMethod",MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable{
        //从动态调用点指定调用testMethod方法并设定好参数类型
        CallSite callsite = (CallSite)MH_BootstrapMethod().invokeWithArguments(MethodHandles.lookup(), "testMethod",
                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return callsite.dynamicInvoker();
    }
}
