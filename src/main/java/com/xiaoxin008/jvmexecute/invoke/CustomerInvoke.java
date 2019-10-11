package com.xiaoxin008.jvmexecute.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 自定义分派规则
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CustomerInvoke {

    class GrandFather{
        void thinking(){
            System.out.println("i am grandfather !");
        }
    }

    class Father extends GrandFather{
        @Override
        void thinking(){
            System.out.println(" i am father ! ");
        }
    }

    class Son extends Father{
        @Override
        void thinking(){
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CustomerInvoke().new Son().thinking();
    }
}
