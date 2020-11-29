package com.xiaoxin008.redis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class LRUCache extends LinkedHashMap<String,String> {

    private int capacity;

    public LRUCache(int capacity) {
        super(16,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(10);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i+"",i+"");
        }
        System.out.println(lruCache);
        String s = lruCache.get("0");
        System.out.println(lruCache);
        lruCache.put("10","10");
        System.out.println(lruCache);
    }
}
