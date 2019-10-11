package com.xiaoxin008.jvmexecute.javac;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class BADLY_NAMED_CODE {

    enum colors{
        red,blue,green;
    }
    static final int _FORTY_TWO = 42;
    public  static  int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE(){
        return;
    }

    public void NOTcamelCASEmethodName(){
        return;
    }
}
