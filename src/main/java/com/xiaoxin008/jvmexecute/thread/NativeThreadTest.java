package com.xiaoxin008.jvmexecute.thread;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class NativeThreadTest {
    
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("please input thread numbers!");
            return;
        }

        int threadNumber = Integer.parseInt(args[0]);

        try {
            for (int i = 0; i < threadNumber; i++) {
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }.start();
                System.out.println("Thread " + i);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
