package com.xiaoxin008.redis.geo;

import com.xiaoxin008.redis.base.RedissonContainer;
import org.redisson.api.*;

import java.util.List;
import java.util.Map;

/**
 * 使用Redis查询附近的人
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithGeoHash {

    private static RedissonClient redissonClient;

    public static void main(String[] args) {
        redissonClient = RedissonContainer.getRedissonClient();
        GeoEntry qc = new GeoEntry(124.011177, 47.34388, "qcxq");
        GeoEntry zy = new GeoEntry(124.0088166560669, 47.34531940537525, "zycs");
        GeoEntry rm = new GeoEntry(124.01653068919373, 47.34182623499159, "rmxx");
        GeoEntry ddj = new GeoEntry(124.01038843054963, 47.34384365087149, "ddjgd");
        addGeos("qqhr",qc,zy,rm,ddj);
        //获取我家到人民小学的距离
        Double distance = getDistance("qqhr", "qcxq", "rmxx");
        System.out.println("我家到人民小学的距离----------"+distance);
        //获取正源超市坐标
        Map<Object, GeoPosition> geo = getGeo("qqhr", "zycs");
        for (Object o : geo.keySet()) {
            System.out.println(o+"------------->"+geo.get(o));
        }
        //获取多多佳糕点的hash值
        Map<Object, String> hash = getHash("qqhr", "ddjgd");
        for (Object o : hash.keySet()) {
            System.out.println(o+"------------->"+hash.get(o));
        }
        //获取20KM离我家最近的建筑物
        List<Object> builds = getRadius("qqhr","qcxq", 20);
        for (Object build : builds) {
            System.out.println(build);
        }
        redissonClient.shutdown();
    }

    public static Long addGeos(String key,GeoEntry...geoEntries){
        RGeo<Object> geo = redissonClient.getGeo(key);
        return geo.add(geoEntries);
    }

    public static Map<Object, GeoPosition> getGeo(String key,Object member){
        RGeo<Object> geo = redissonClient.getGeo(key);
        return geo.pos(member);
    }

    public static Map<Object, String> getHash(String key,Object member){
        RGeo<Object> geo = redissonClient.getGeo(key);
        return geo.hash(member);
    }

    public static Double getDistance(String key,Object start,Object end){
        RGeo<Object> geo = redissonClient.getGeo(key);
        return geo.dist(start,end, GeoUnit.METERS);
    }

    public static List<Object> getRadius(String key,Object member,double radius){
        RGeo<Object> geo = redissonClient.getGeo(key);
        return geo.radius(member, radius, GeoUnit.KILOMETERS, GeoOrder.ASC);
    }
}
