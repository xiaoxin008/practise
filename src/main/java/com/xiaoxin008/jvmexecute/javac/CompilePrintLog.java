package com.xiaoxin008.jvmexecute.javac;

/**
 * VM args：-XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+PrintInlining
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CompilePrintLog {

    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        for (int j = 0; j<100000;j++ );
        return i*2;
    }

    public static long calcSum(){
        long sum = 0;
        for(int i = 1;i<=100;i++){
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for(int i = 0;i<NUM;i++){
            calcSum();
        }
    }
}
