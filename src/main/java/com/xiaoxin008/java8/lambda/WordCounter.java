package com.xiaoxin008.java8.lambda;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 使用函数统计字符中的单词个数（每个单词使用空格隔开）
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class WordCounter {

    private final int counter;

    private final boolean isLastSpace;

    public WordCounter(int counter, boolean isLastSpace) {
        this.counter = counter;
        this.isLastSpace = isLastSpace;
    }

    //转换函数
    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            //如果此字符为空格
            //且上一个不为空格的时候 返回对象 计数器不变 上一个字符设为空格
            return isLastSpace?this:new WordCounter(counter,true);
        }else{
            //如果此字符不为空格
            //且上一个为空格的时候 返回对象 计数器+1 上一个字符设为不是空格
            return isLastSpace?new WordCounter(counter+1,false):this;
        }
    }

    //归约函数
    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter+wordCounter.counter,wordCounter.isLastSpace);
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        final String SENTENCE = " Nel mezzo del cammin di nostra vita mi ritrovai inuna selva oscura ché la dritta via era smarrita xxxx ";
        Stream<Character> characterStream = IntStream.rangeClosed(0, SENTENCE.length()-1).mapToObj(SENTENCE::charAt);
        //因为原始的String在任意位置拆分，所以有时一个词会被分为两个词，然后数了两次。这就说明，拆分流会影响结果，而把顺序流换成并行流就可能使结果出错
        //所以我们要自定义拆分器只在词尾进行拆分 然后再并行执行
        WordCounter reduce1 = characterStream.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        int counter1 = reduce1.getCounter();
        System.out.println(counter1);

        //使用自定义分割器进行拆分
        WordCounterSpliterator wordCounterSpliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(wordCounterSpliterator, true);
        WordCounter reduce2 = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        int counter2 = reduce2.getCounter();
        System.out.println(counter2);
    }
}
