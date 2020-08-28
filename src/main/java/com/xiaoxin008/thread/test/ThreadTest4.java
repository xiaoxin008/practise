package com.xiaoxin008.thread.test;

import java.util.Arrays;

/**
 * 不可变容器+valatile
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ThreadTest4 {

    private static class NumContainer {
        private final Integer m;
        private final Integer[] n;

        public NumContainer(Integer m, Integer[] n) {
            this.m = m;
            this.n = n;
        }

        public Integer[] getN(Integer num) {
            if (!num.equals(m) || m == null) {
                return null;
            } else {
                return Arrays.copyOf(n, n.length);
            }
        }

        @Override
        public String toString() {
            return "NumContainer{" +
                    "m=" + m +
                    ", n=" + Arrays.toString(n) +
                    '}';
        }
    }

    private static class NumCache {

        private volatile NumContainer cache = new NumContainer(null, new Integer[]{0});

        public void service(Integer num) {
            Integer[] n = cache.getN(num);
            if (n == null) {
                n = new Integer[]{num, num + 1};
                cache = new NumContainer(num, n);
            }
        }

        public NumContainer getCache() {
            return cache;
        }
    }

    public static void main(String[] args) {
        NumCache numCache = new NumCache();
        for (int i = 0; i < 10000; i++) {
            final int n = i;
            Thread thread = new Thread(() -> numCache.service(n));
            thread.start();
        }
        System.out.println(numCache.getCache().toString());
    }

}
