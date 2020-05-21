package com.xiaoxin008.spring.aop2;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Aspect
public class ManAspect {

    @DeclareParents(value = "com.xiaoxin008.spring.aop2.*",defaultImpl = Teacher.class)
    private Woker woker;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application-context-aspectJ-auto.xml");
        Object obj1 = cp.getBean("man");
        Object obj2 = cp.getBean("man");
        Object obj3 = cp.getBean("myMethod");
        Object obj4 = cp.getBean("myMethod");
        //man对象为多例
        Father man1 = (Man) obj1;
        Father man2 = (Man) obj2;
        //method对象为单例
        MyMethod myMethod1 = (MyMethod) obj3;
        MyMethod myMethod2 = (MyMethod) obj4;
        man1.baby();
        myMethod1.method1();
        Woker teacher1 = (Woker) obj1;
        Woker teacher2 = (Woker) obj2;
        Woker teacher3 = (Woker) obj3;
        Woker teacher4 = (Woker) obj4;
        System.out.println(teacher1.getCode());
        System.out.println(teacher2.getCode());
        System.out.println(teacher3.getCode());
        System.out.println(teacher4.getCode());

//        result
//        喜当爹！
//        method1 execute!

//        9677b112-7d51-435d-94fb-2c54ac3ec8cd
//        97701f21-06f6-4710-8a5c-32d2718cb082

//        b4b47b8e-9a3d-4568-87af-435ed01ac466
//        b4b47b8e-9a3d-4568-87af-435ed01ac466
    }
}
