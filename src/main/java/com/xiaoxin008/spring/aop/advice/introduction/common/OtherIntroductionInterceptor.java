package com.xiaoxin008.spring.aop.advice.introduction.common;

import com.xiaoxin008.spring.aop.advice.introduction.IOther;
import com.xiaoxin008.spring.aop.advice.introduction.ISome;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IntroductionInterceptor
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class OtherIntroductionInterceptor implements IntroductionInterceptor, IOther {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //判断调用方调用的是原生类里的方法还是增强接口里定义的方法
        Class<?> declaringClass = methodInvocation.getMethod().getDeclaringClass();
        if(implementsInterface(declaringClass)){
            //如果是增强接口定义的方法，直接拿出Method对象反射调用实现类
            return methodInvocation.getMethod().invoke(this,methodInvocation.getArguments());
        }else{
            //如果是原生类里的方法，直接按原调用链执行即可
            return methodInvocation.proceed();
        }
    }

    @Override
    public boolean implementsInterface(Class<?> intf) {
        //指定拦截的增强接口
        return intf.isAssignableFrom(IOther.class);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context-introduction.xml");
        ISome some=(ISome)context.getBean("proxyFactoryBean");
        some.doSome();
        ((IOther)some).doOther();
    }

}
