package com.xiaoxin008.java8.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 自定义质数收集器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class PrimeCollector implements Collector<Integer,Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    //初始化累加器
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){{
            put(true,new ArrayList<Integer>());
            put(false,new ArrayList<Integer>());
        }};
    }

    //元素加入到累加器
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (map,num) -> map.get(isPrime(num)).add(num);
    }

    //并行如何合并
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1,map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }

    private boolean isPrime(Integer num){
        return !(IntStream.range(2, num).boxed().anyMatch(x -> x.intValue() != num && num % x == 0));
    }

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, 10000).boxed().collect(new PrimeCollector());
        System.out.println(collect);
    }
}
