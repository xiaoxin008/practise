package com.xiaoxin008.jvmexecute.dispatch;

/**
 * 动态分派
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DynamicDispatch {

    public abstract static class Human{
        protected abstract void sayHello();
    }

    public static class Woman extends Human{
        @Override
        protected void sayHello() {
            System.out.println("woman say hello !");
        }
    }

    public static class Man extends Human{
        @Override
        protected void sayHello() {
            System.out.println("man say hello !");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
