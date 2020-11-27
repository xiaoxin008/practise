package com.xiaoxin008.redis.stream;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异步队列
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithStream {

    private static Jedis jedis = JedisContainer.getJedis();

    public static void main(String[] args) {
//        add();
        //队列长度
//        long len = len();
//        System.out.println(len);
        //遍历队列
//        List<StreamEntry> list = list();
//        list.stream().forEach(System.out::println);
        //删除队列
//        long del = del("1606470108712-0");
//        System.out.println(del);
//
//        List<StreamEntry> after = list();
//        after.stream().forEach(System.out::println);

//        read();
//        group();
        streamInfo();
        groupInfo();
          groupRead();
        ack();
    }

    public static void add(){
//        Map<String, String> book1 = new HashMap<>(2);
//        book1.put("name","格林童话");
//        book1.put("price","12");
//        StreamEntryID books1 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book1);
//        System.out.println(books1);
//        Map<String, String> book2 = new HashMap<>(2);
//        book2.put("name","狼图腾");
//        book2.put("price","32");
//        StreamEntryID books2 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book2);
//        System.out.println(books2);
//        Map<String, String> book3 = new HashMap<>(2);
//        book3.put("name","Java入门");
//        book3.put("price","112");
//        StreamEntryID books3 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book3);
//        System.out.println(books3);
//        Map<String, String> book4 = new HashMap<>(2);
//        book4.put("name","故事会全集");
//        book4.put("price","192");
//        StreamEntryID books4 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book4);
//        System.out.println(books4);

//        Map<String, String> book5= new HashMap<>(2);
//        book5.put("name","百科全书");
//        book5.put("price","1912");
//        StreamEntryID books5 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book5);
//        System.out.println(books5);

//        Map<String, String> book6= new HashMap<>(2);
//        book6.put("name","鬼故事");
//        book6.put("price","1912");
//        StreamEntryID books6 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book6);
//        System.out.println(books6);

        Map<String, String> book7= new HashMap<>(2);
        book7.put("name","鬼故事2");
        book7.put("price","1912");
        StreamEntryID books7 = jedis.xadd("books", StreamEntryID.NEW_ENTRY, book7);
        System.out.println(books7);
    }

    public static long len(){
        return jedis.xlen("books");
    }

    public static List<StreamEntry> list(){
        return jedis.xrange("books", null, null, 100);
    }

    public static long del(String id){
        return jedis.xdel("books",new StreamEntryID(id));
    }

    public static void read(){
        List<Map.Entry<String, List<StreamEntry>>> xread = jedis.xread(5, 0, new Map.Entry<String, StreamEntryID>() {
            @Override
            public String getKey() {
                return "books";
            }

            @Override
            public StreamEntryID getValue() {
                return new StreamEntryID();
            }

            @Override
            public StreamEntryID setValue(StreamEntryID value) {
                return new StreamEntryID();
            }
        });
        for (Map.Entry<String, List<StreamEntry>> entry : xread) {
            System.out.println(entry);
        }
    }

    public static void group(){
        String s = jedis.xgroupCreate("books", "reader2", new StreamEntryID(), false);
        System.out.println(s);
    }

    public static void streamInfo(){
        StreamInfo books = jedis.xinfoStream("books");
        Map<String, Object> streamInfo = books.getStreamInfo();
        for (String s : streamInfo.keySet()) {
            System.out.println(s+"---------"+streamInfo.get(s));
        }
    }

    public static void groupInfo(){
        List<StreamGroupInfo> reader = jedis.xinfoGroup("books");
        for (StreamGroupInfo streamGroupInfo : reader) {
            Map<String, Object> gi = streamGroupInfo.getGroupInfo();
            for (String s : gi.keySet()) {
                System.out.println(s+"--------------"+gi.get(s));
            }
        }
    }

    public static void groupRead(){
        List<Map.Entry<String, List<StreamEntry>>> reader = jedis.xreadGroup("reader2", "r2", 5, 0, false, new Map.Entry<String, StreamEntryID>() {
            @Override
            public String getKey() {
                return "books";
            }

            @Override
            public StreamEntryID getValue() {
                return StreamEntryID.UNRECEIVED_ENTRY;
            }

            @Override
            public StreamEntryID setValue(StreamEntryID value) {
                return StreamEntryID.UNRECEIVED_ENTRY;
            }
        });
        System.out.println(reader);
    }

    public static void ack(){
        long xack = jedis.xack("books", "reader1", new StreamEntryID("1606476694067-0"));
        System.out.println(xack);
    }
}
