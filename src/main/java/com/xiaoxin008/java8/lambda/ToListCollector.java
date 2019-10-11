package com.xiaoxin008.java8.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自己构建toList收集器
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ToListCollector<T> implements Collector<T,List<T>,List<T>> {

    //新建结果收集器
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    //元素加入到结果收集器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list,item) -> list.add(item);
    }

    //如果采用并行方式如何组合两个元素
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    //结果返回恒等函数
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    //为收集器添加IDENTITY_FINISH和CONCURRENT标志
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }

    //测试
    public static void main(String[] args) {

        StreamPractise.Trader raoul = new StreamPractise.Trader("Raoul", "Cambridge");
        StreamPractise.Trader mario = new StreamPractise.Trader("Mario", "Milan");
        StreamPractise.Trader alan = new StreamPractise.Trader("Alan", "Cambridge");
        StreamPractise.Trader brian = new StreamPractise.Trader("Brian", "Cambridge");

        List<StreamPractise.Transaction> transactions = Arrays.asList(
                new StreamPractise.Transaction(brian, 2011, 300),
                new StreamPractise.Transaction(raoul, 2012, 1000),
                new StreamPractise.Transaction(raoul, 2011, 400),
                new StreamPractise.Transaction(mario, 2012, 710),
                new StreamPractise.Transaction(mario, 2012, 700),
                new StreamPractise.Transaction(alan, 2012, 950)
        );

        List<StreamPractise.Trader> result = transactions.parallelStream().map(StreamPractise.Transaction::getTrader).collect(new ToListCollector<>());
        result.stream().forEach(System.out::println);

    }
}
