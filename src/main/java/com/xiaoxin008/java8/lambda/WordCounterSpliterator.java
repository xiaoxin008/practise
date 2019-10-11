package com.xiaoxin008.java8.lambda;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 单词拆分器（只在词尾进行拆分）
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String str;

    private int currentChar = 0;

    public WordCounterSpliterator(String str) {
        this.str = str;
    }

    /**
     * tryAdvance方法把String中当前位置的Character传给了Consumer，并让位置加一
     * 如果新的指针位置小于String的总长，且还有要遍历的Character， 则tryAdvance返回true
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(str.charAt(currentChar++));
        return currentChar < str.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = str.length() - currentChar;
        //这里表示当前剩余要解析的字符长度如果小于10，说明已经足够小，无须再次拆分，可以顺序执行
        if (currentSize < 10) {
            return null;
        }
        //将试探拆分位置设定为str的中间
        for(int splitPos = currentChar + currentSize/2;splitPos < str.length();splitPos++){
            //让拆分位置前进到下一个空格
            if(Character.isWhitespace(str.charAt(splitPos))){
                //创建一个新的拆分器拆分开始位置到拆分位置的部分
                WordCounterSpliterator wordCounterSpliterator = new WordCounterSpliterator(str.substring(currentChar,splitPos));
                //把这个拆分器的当前位置设为拆分位置
                currentChar = splitPos;
                //返回子拆分器
                return wordCounterSpliterator;
            }
        }
        return null;
    }

    /**
     *还需要遍历的元素的estimatedSize就是这个Spliterator解析的String的总长度和当前遍历的位置的差
     */
    @Override
    public long estimateSize() {
        return str.length() - currentChar;
    }

    /**
     * 最后，characteristic方法告诉框架这个Spliterator是ORDERED（顺序就是String
     * 中各个Character的次序）、SIZED（estimatedSize方法的返回值是精确的）、
     * SUBSIZED（trySplit方法创建的其他Spliterator也有确切大小）、NONNULL（String
     * 中不能有为 null 的 Character ） 和 IMMUTABLE （在解析 String 时不能再添加
     * Character，因为String本身是一个不可变类）的
     * @return
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
