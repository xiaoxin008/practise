package com.xiaoxin008.spring.aop.weaver;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Introduction的织入
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class IntroductionPerformanceAdvice extends DelegatingIntroductionInterceptor{

    private boolean busyAsTester;

    public IntroductionPerformanceAdvice(Object delegate) {
        super(delegate);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        super.invoke(mi);
        if(isBusyAsTester() && "develop".equals(mi.getMethod().getName())){
            System.out.println("你想累死我啊！");
        }
        return null;
    }

    public boolean isBusyAsTester() {
        return busyAsTester;
    }

    public void setBusyAsTester(boolean busyAsTester) {
        this.busyAsTester = busyAsTester;
    }

    public interface IDevelop {
        default void develop(){
            System.out.println("开发....");
        }
    }

    static class Developer implements IDevelop{
        @Override
        public void develop() {
            System.out.println("新开发........");
        }
    }

    public interface ITest {
        default void test(){
            System.out.println("测试....");
        }
    }

    public static void main(String[] args) {
        ProxyFactory weaver = new ProxyFactory(new Developer());
        weaver.setProxyTargetClass(true);
        IntroductionPerformanceAdvice advice = new IntroductionPerformanceAdvice(new ITest() {
        });
        advice.setBusyAsTester(true);
        DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor(advice,advice);
        weaver.addAdvisor(advisor);
        ITest tester = (ITest) weaver.getProxy();
        tester.test();
        IDevelop developer = (IDevelop)weaver.getProxy();
        developer.develop();
    }
}
