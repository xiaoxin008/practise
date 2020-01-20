package com.xiaoxin008.spring.ioc.bean.replacer;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * 替换getPeach方法的代理类
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class PeachProviderMethodReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("警告，方法被替换掉了");
        return null;
    }

//    xml配置文件
//    <bean id="shop" class="com.xiaoxin008.spring.ioc.bean.common.Shop">
//        <replaced-method name="getPeach" replacer="peachReplacer"/>
//    </bean>
//
//    <bean id="peachReplacer" class="com.xiaoxin008.spring.ioc.bean.replacer.PeachProviderMethodReplacer">
//    </bean>
}
