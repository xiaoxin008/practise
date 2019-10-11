package com.xiaoxin008.java8.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Stream练习题
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class StreamPractise {

    //交易员对象
    static class Trader{
        private final String name;
        private final String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Trader："+this.name+" in "+this.city;
        }
    }

    //交易对象
    static class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "trader=" + trader +
                    ", year=" + year +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //找出2011年发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> practise1 = transactions.parallelStream()
                .filter(transaction -> 2011 == transaction.getYear())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(practise1);

        //交易员都在哪些不同的城市工作过
        List<String> practise2 = transactions.parallelStream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(practise2);

        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> practise3 = transactions.parallelStream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(practise3);

        //返回所有交易员的姓名字符串，按照字母顺序排序
        String practise4 = transactions.parallelStream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(practise4);

        //有没有交易员是在米兰工作的
        Trader practise5 = transactions.parallelStream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Milan".equals(trader.getCity()))
                .findAny().get();
        System.out.println(practise5);

        //打印生活在剑桥的交易员的所有交易额
        Map<String, Integer> practise6 = transactions.parallelStream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .collect(Collectors.groupingBy(x -> x.getTrader().getName(), Collectors.mapping(Transaction::getValue, Collectors.reducing(0, Integer::sum))));
        System.out.println(practise6);

        //所有交易中，最高的交易额是多少
        Integer practise7 = transactions.parallelStream()
                .map(Transaction::getValue)
                .reduce(Integer::max).get();
        System.out.println(practise7);

        //找到交易额最小的交易
        int practise8 = transactions.parallelStream().mapToInt(Transaction::getValue).min().orElse(0);
        System.out.println(practise8);

        //获取交易的聚合属性
        IntSummaryStatistics collect10 = transactions.parallelStream().collect(Collectors.summarizingInt(Transaction::getValue));
        System.out.println(collect10);

        //找到1~100之内的偶数个数
        long practise10 = IntStream.rangeClosed(1, 101).filter(n -> n % 2 == 0).count();
        System.out.println(practise10);

        //找到1~99中符合勾股定理的数组
        List<double[]> practise11 = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .mapToObj(b -> new double[]{a, b, (double) Math.sqrt(a * a + b * b)}))
                .filter(arr -> arr[2] % 1 == 0)
                .collect(Collectors.toList());
        practise11.stream().forEach(x -> {
            System.out.println("----------------------");
            Arrays.stream(x).forEach(System.out::println);
        });

        //找到100以内的质数和非质数
        Map<Boolean, List<Integer>> practise12 = IntStream.rangeClosed(2, 100).boxed().collect(Collectors.partitioningBy(x -> {
            if(IntStream.rangeClosed(2, x.intValue()).boxed().anyMatch(y -> x % y.intValue() == 0 && x != y.intValue())){
                return false;
            }else{
                return true;
            }
        }));
        System.out.println(practise12);

        //把两个集合组合成新集合（A,B）且能被二者之和能被2整数的集合
        List<Integer> integer1 = Arrays.asList(1, 2, 3);
        List<Integer> integer2 = Arrays.asList(3, 4);
        List<int[]> practise13 = integer1.stream().
                flatMap(x -> integer2.stream().map(y -> new int[]{x, y}).filter(arr -> (arr[0] + arr[1]) % 2 == 0))
                .collect(Collectors.toList());
        practise13.stream().forEach(x -> {
            System.out.println("----------------");
            Arrays.stream(x).forEach(System.out::println);
        });
    }

}
