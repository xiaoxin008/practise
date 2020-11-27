package com.xiaoxin008.redis.cluster.rediscluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用Redis-Cluster
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class RedisWithCluster {

    public static void main(String[] args) {
        // 添加集群的服务节点Set集合
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("192.168.91.132", 6379));
        hostAndPortsSet.add(new HostAndPort("192.168.91.132", 6380));
        hostAndPortsSet.add(new HostAndPort("192.168.91.132", 6381));
        hostAndPortsSet.add(new HostAndPort("192.168.91.132", 6382));
        hostAndPortsSet.add(new HostAndPort("192.168.91.132", 6383));
        hostAndPortsSet.add(new HostAndPort("192.168.91.132", 6384));

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);

        JedisCluster jedisCluster = new JedisCluster(hostAndPortsSet,jedisPoolConfig);

        jedisCluster.set("wfx","123");
        String s = jedisCluster.get("wfx");
        String s1 = jedisCluster.get("hello");
        System.out.println(s);
        System.out.println(s1);

    }
}