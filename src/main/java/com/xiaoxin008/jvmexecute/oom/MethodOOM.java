package com.xiaoxin008.jvmexecute.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区-OOM异常
 * VM Args：-XX:PermSize=3M -XX:MaxPermSize=3M
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MethodOOM {

    public static void main(String[] args) {
        int i = 0;
        List<String> methodList = new ArrayList<>();
        while (true){
            methodList.add(String.valueOf(i++).intern());
        }
    }
}
