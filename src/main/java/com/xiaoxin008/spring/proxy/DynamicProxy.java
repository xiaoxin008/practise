package com.xiaoxin008.spring.proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DynamicProxy {

    interface HttpLauncher{
       default void request(){
           System.out.println("launcher request !!! ");
       }
       default void response(){
           System.out.println("launcher response !!! ");
       }
    }

    public static void main(String[] args) {
        HttpLauncher instance = (HttpLauncher)Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{HttpLauncher.class},
                ((proxy, method, arg) -> {
                    System.out.println("动态代理处理过程！！！");
                    return method.invoke(new HttpLauncher() {
                    }, arg);
                }));
        instance.request();
        instance.response();
    }

}
