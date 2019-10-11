package com.xiaoxin008.jvmexecute.gc;

/**
 * 1.对象可以在被GC时自我拯救
 * 2.这种自救机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 * 3.不推荐此方法
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes，i am still alive !");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(" finalize method executed ");
        //重新与引用链上的任何一个对象建立关系即可，
        // 如：把自己（this）赋值给某个类变量或者对象的成员变量
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception{
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no，i am dead !");
        }
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no，i am dead !");
        }
    }
}
