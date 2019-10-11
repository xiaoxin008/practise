package com.xiaoxin008.jvmexecute.loader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 如果一个类由两个不同的类加载器加载进来，这两个类是否相同
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DifferentClassloader {

    public static void main(String[] args) throws Exception{
        ClassLoader classLoader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(filename);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = classLoader.loadClass("com.xiaoxin008.jvmexecute.loader.DifferentClassloader").newInstance();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Object systemObj = systemClassLoader.loadClass("com.xiaoxin008.jvmexecute.loader.DifferentClassloader").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.xiaoxin008.jvmexecute.loader.DifferentClassloader);
        System.out.println(systemObj instanceof com.xiaoxin008.jvmexecute.loader.DifferentClassloader);
    }
}
