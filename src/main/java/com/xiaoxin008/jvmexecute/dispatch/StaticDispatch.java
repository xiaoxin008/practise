package com.xiaoxin008.jvmexecute.dispatch;

/**
 * 静态分派
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class StaticDispatch {

    public static class Human{

    }

    public static class Woman extends Human{

    }

    public static class Man extends Human{

    }

    public void sayHello(Human human){
        System.out.println("human say hello !");
    }

    public void sayHello(Woman woman){
        System.out.println("woman say hello !");
    }

    public void sayHello(Man man){
        System.out.println("man say hello !");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
