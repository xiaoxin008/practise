package com.xiaoxin008.spring.aop.auto;

import com.xiaoxin008.spring.aop.AnyBean;
import com.xiaoxin008.spring.aop.IOther;
import com.xiaoxin008.spring.aop.ISome;
import com.xiaoxin008.spring.aop.SomeBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试DefaultAdvisorAutoProxyCreator功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DefaultAdvisorAutoProxy {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-default-auto.xml");
        Object obj = cp.getBean("someBean");
        SomeBean sb = (SomeBean) obj;
        sb.doSome();
        IOther other = (IOther) obj;
        other.doOther();
        Object obj1= cp.getBean("anyBean");
        AnyBean anyBean = (AnyBean) obj1;
        anyBean.doAnying();
        IOther other1 = (IOther) obj1;
        other1.doOther();
    }

}
