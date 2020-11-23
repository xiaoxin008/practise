package com.xiaoxin008.redis.limit.funnel;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class FunnelLimiter {

    private static Map<String,Funnel> funnelMap = new HashMap<>();

    public static class Funnel{

        //漏斗容量
        private int capacity;
        //剩余容量
        private int leftQuota;
        //流水速率
        private double leakingRate;
        //上次漏水时间
        private long leakingTs;

        public Funnel(int capacity, double leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace(){
            //先拿到当前时间
            long currentTimeMillis = System.currentTimeMillis();
            //当前时间 - 上次漏水时间 中间隔了多久
            long deltaTs = currentTimeMillis - this.leakingTs;
            //腾出多少容量
            int deltaQuota = (int) (deltaTs * leakingRate);

            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.leakingTs = currentTimeMillis;
                return;
            }
            if (deltaQuota < 1) { // 腾出空间太小，最小单位是 1
                return;
            }
            leftQuota += deltaQuota;
            leakingTs = currentTimeMillis;
            if(leftQuota > capacity){
                leftQuota = capacity;
            }
        }

        /**
         * 把剩余量减少
         * @param quota
         * @return
         */
        boolean watering(int quota){
            makeSpace();
            if(this.leftQuota >= quota){
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    public static boolean isAllowed(String userId,String actionKey,int capacity,float leakingRate){
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnelMap.get(key);
        if(funnel == null){
            funnel = new Funnel(capacity, leakingRate);
            funnelMap.put(key,funnel);
        }
        return funnel.watering(1);
    }

    public static void main(String[] args) {
        boolean allowed ;
        for (int i = 0;i < 100;i++) {
            allowed = isAllowed("wfx", "funnel", 10, (float) 0.1);
            System.out.println(allowed);
        }
    }
}
