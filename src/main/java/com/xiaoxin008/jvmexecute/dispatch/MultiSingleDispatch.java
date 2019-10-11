package com.xiaoxin008.jvmexecute.dispatch;

/**
 * 单分派与多分派演示
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MultiSingleDispatch {

    public static class QQ{

    }

    public static class Wechat{

    }

    public static class Father{
        public void hardChoice(QQ arg){
            System.out.println("father use qq!");
        }

        public void hardChoice(Wechat wechat){
            System.out.println("father use wechat!");
        }
    }

    public static class Son extends Father{
        @Override
        public void hardChoice(QQ arg){
            System.out.println("son use qq!");
        }

        @Override
        public void hardChoice(Wechat wechat){
            System.out.println("son use wechat!");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new Wechat());
        son.hardChoice(new QQ());
    }
}
