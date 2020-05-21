package com.xiaoxin008.spring.aop2;

import org.springframework.aop.framework.AopContext;

/**
 * 目标类
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MyMethod {


    public void method1(){
        System.out.println("method1 execute!");
//        MyMethod proxy = (MyMethod)AopContext.currentProxy();
//        proxy.method2();
    }

    public void method2(){
        System.out.println("method2 execute!");
    }

    @Deprecated
    public void method3(String obj,String name,String age){
        int length = obj.length();
        System.out.println("method3 execute!");
    }

    public String method4(String obj,String name,String age){
        System.out.println("method4 execute!");
        return "对象："+obj+" 姓名："+name+" 年龄："+age;
    }
}
