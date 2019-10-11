package com.xiaoxin008.jvmexecute.sof;

/**
 * Java栈-SOF异常
 * VM Args: -Xss128k
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class StackSOF {

    public static class StackSOFObject{

        private int stackLength = 1;

        public void stackLeak(){
            stackLength++;
            stackLeak();
        }
    }

    public static void main(String[] args) {
        StackSOFObject stackSOFObject = new StackSOFObject();
        try {
            stackSOFObject.stackLeak();
        } catch (Throwable e) {
            System.out.println(" stack length: "+stackSOFObject.stackLength);
            throw e;
        }
    }
}
