package com.xiaoxin008.jvmexecute.sof;

/**
 * Java栈-创建线程SOF异常(系统会卡死，别轻易尝试)
 * VM Args：-Xss2M
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class StackThreadSOF {

    public static class StackThread extends Thread{
        @Override
        public void run() {
            while (true){

            }
        }
    }

    public static void main(String[] args) {
        while (true){
            StackThread stackThread = new StackThread();
            stackThread.start();
        }
    }
}
