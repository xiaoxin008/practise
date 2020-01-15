package com.xiaoxin008.spring.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态字节码
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DynamicClass {

    static class HttpEnhancerImpl implements DynamicProxy.HttpLauncher{

    }

    static class RequestCtrlCallback implements MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("动态字节码增强！");
            //调用了invokeSuper方法
            return methodProxy.invokeSuper(o,args);
        }
    }

    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\WFX\\Desktop");
        //1、创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //2、设置父类class对象
        enhancer.setSuperclass(DynamicClass.HttpEnhancerImpl.class);
        //3、设置回调
        enhancer.setCallback(new RequestCtrlCallback());
        //4、创建子类代理
        DynamicProxy.HttpLauncher httpLauncher = (DynamicProxy.HttpLauncher) enhancer.create();
        //5、使用方法
        httpLauncher.response();
//        int i = "CGLIB$response$5".toString().hashCode();
//        System.out.println(i);
    }
}
