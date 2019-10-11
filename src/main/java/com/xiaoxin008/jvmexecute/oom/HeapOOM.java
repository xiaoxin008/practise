package com.xiaoxin008.jvmexecute.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆-OOM异常
 * VM Args：-Xms20M -Xmx20M -XX：+HeapDumpOnOutOfMemoryError
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
