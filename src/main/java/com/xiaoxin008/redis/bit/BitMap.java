package com.xiaoxin008.redis.bit;

import com.xiaoxin008.redis.base.JedisContainer;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;

/**
 * 位图
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class BitMap {

    public static void main(String[] args) {
        magic();
    }

    //零存整取
    public static void zeroDeposit() {
        Jedis jedis = JedisContainer.getJedis();
        buildString(jedis);
        String result = jedis.get("s");
        System.out.println(result);
        jedis.del("s");
        jedis.close();
    }

    //零存零取
    public static void zeroDepositAndFetch() {
        Jedis jedis = JedisContainer.getJedis();
        buildString(jedis);

        Boolean s1 = jedis.getbit("s", 1);
        System.out.println(s1);

        Boolean s2 = jedis.getbit("s", 2);
        System.out.println(s2);

        Boolean s7 = jedis.getbit("s", 7);
        System.out.println(s7);

        jedis.del("s");
        jedis.close();
    }

    //整存零取
    public static void zeroFetch() {
        Jedis jedis = JedisContainer.getJedis();
        jedis.set("s","WFX");
        Boolean s7 = jedis.getbit("s", 7);
        System.out.println(s7);
        Boolean s8 = jedis.getbit("s", 8);
        System.out.println(s8);
        jedis.del("s");
        jedis.close();
    }

    //位图统计
    public static void statistics() {
        Jedis jedis = JedisContainer.getJedis();
        jedis.set("s","WFX");
        //所有字符一共有多少个1
        Long s = jedis.bitcount("s");
        System.out.println(s);
        //第一个字符有多少个1
        Long s1 = jedis.bitcount("s",0,0);
        System.out.println(s1);
        //第二个字符有多少个1
        Long s2 = jedis.bitcount("s",1,1);
        System.out.println(s2);
        //前二个字符有多少个1
        Long s_12 = jedis.bitcount("s",0,1);
        System.out.println(s_12);
        jedis.del("s");
        jedis.close();
    }

    //位图统计
    public static void search() {
        Jedis jedis = JedisContainer.getJedis();
        jedis.set("s","WFX");
        //第一个1位
        Long s1 = jedis.bitpos("s", true);
        System.out.println(s1);
        //第一个0位
        Long sf = jedis.bitpos("s", false);
        System.out.println(sf);
        //最后一个字符的第一个1位
        Long sn = jedis.bitpos("s", true, new BitPosParams(-1, -1));
        System.out.println(sn);
        jedis.del("s");
        jedis.close();
    }

    //魔术指令 bitfield set/get/incrby
    public static void magic() {
        Jedis jedis = JedisContainer.getJedis();
        jedis.set("s","WFX");
        //从第16位往后取8位，结果为无符号数
        List<Long> s = jedis.bitfield("s", "get","u8","16");
        Iterator<Long> siterator = s.iterator();
        while (siterator.hasNext()){
            System.out.println(siterator.next());
        }
        //从第8位往后取8位，此无符号数设置为84(T)
        jedis.bitfield("s", "set","u8","8","84");
        System.out.println(jedis.get("s"));

        //从第8位往后取8位，此无符号数设置为84(T)
        jedis.bitfield("s", "set","u4","0","15");

        //从第16位往后取8位，结果为无符号数
        List<Long> s1 = jedis.bitfield("s", "get","u4","0");
        Iterator<Long> s1iterator = s1.iterator();
        while (s1iterator.hasNext()){
            System.out.println(s1iterator.next());
        }

        //从第8位往后取8位，此无符号数+1，溢出不执行
        jedis.bitfield("s", "overflow","fail","incrby","u4","0","4");

        //从第16位往后取8位，结果为无符号数
        List<Long> s2 = jedis.bitfield("s", "get","u4","0");
        Iterator<Long> s2iterator = s2.iterator();
        while (s2iterator.hasNext()){
            System.out.println(s2iterator.next());
        }

        //从第0位往后取4位，此无符号数+1，溢出保持最大值
        jedis.bitfield("s", "overflow","sat","incrby","u4","0","4");

        //从第16位往后取8位，结果为无符号数
        List<Long> s3 = jedis.bitfield("s", "get","u4","0");
        Iterator<Long> s3iterator = s3.iterator();
        while (s3iterator.hasNext()){
            System.out.println(s3iterator.next());
        }

        jedis.del("s");
        jedis.close();
    }

    public static void buildString(Jedis jedis) {
        jedis.setbit("s", 1, true);
        jedis.setbit("s", 3, true);
        jedis.setbit("s", 5, true);
        jedis.setbit("s", 6, true);
        jedis.setbit("s", 7, true);

        jedis.setbit("s", 9, true);
        jedis.setbit("s", 13, true);
        jedis.setbit("s", 14, true);

        jedis.setbit("s", 17, true);
        jedis.setbit("s", 19, true);
        jedis.setbit("s", 20, true);
    }
}
