package com.xiaoxin008.spring.aop.advice.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.math.IntRange;

/**
 * 模拟打折
 * 当商品价格为50-100之间打5折
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DiscountMethodInterceptor implements MethodInterceptor {

    private static final Integer DEFAULT_DISCOUNT_RATIO = 50;

    private static final IntRange DISCOUNT_PRICE_RANGE =  new IntRange(50,100);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //让程序按照原来的调用链运行
        int price = (int)invocation.proceed();
        if(DISCOUNT_PRICE_RANGE.containsInteger(price)){
            return (price * DEFAULT_DISCOUNT_RATIO) / 100;
        }
        return price;
    }

    public static Integer getDefaultDiscountRatio() {
        return DEFAULT_DISCOUNT_RATIO;
    }

    public static IntRange getDiscountPriceRange() {
        return DISCOUNT_PRICE_RANGE;
    }
}
