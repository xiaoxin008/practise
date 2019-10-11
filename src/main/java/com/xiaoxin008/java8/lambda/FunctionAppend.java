package com.xiaoxin008.java8.lambda;

import java.util.function.Function;

/**
 * 使用函数方法进行字符串拼接
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class FunctionAppend {

    public static String addHeader(String str){
        return "add header："+str;
    }

    public static String addBody(String str){
        return str+" add body ...";
    }

    public static void main(String[] args) {
        String test = "测试";
        Function<String, String> addHeader = FunctionAppend::addHeader;
        Function<String, String> stringStringFunction = addHeader.andThen(FunctionAppend::addBody);
        String apply = stringStringFunction.apply(test);
        System.out.println(apply);
    }
}
