package com.relly.video.common;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 简单本地缓存器,适用于小规模的静态数据缓存。
 * 失效策略："惰性删除"，即每次查询时进行失效判断
 * 淘汰策略: 无
 * @author rellyCheng
 * @date 2019/5/9 15:18
 * @return
 */
public class SimpleLocalCache {

    //ConcurrentHashMap并发读写
    private static ConcurrentHashMap<String, CacheData> cacheArea = new ConcurrentHashMap<>();

    //取缓存
    public static Object get(String key) {
        CacheData cacheData = cacheArea.get(key);
        if (cacheData == null) {
            return null;
        }
        if (isDead(cacheData)) {
            //惰性删除
            remove(key);
            return null;
        }
        return cacheData.getValue();
    }

    //放缓存
    public static boolean put(String key, Object value, long maxAge) {
        CacheData data = new CacheData();
        data.setCreateTime(new Date().getTime());
        data.setMaxAge(maxAge);
        data.setKey(key);
        data.setValue(value);
        cacheArea.put(key, data);
        return false;
    }

    //是否过期
    private static boolean isDead(CacheData data) {
        if (data == null) {
            return true;
        }
        if (data.getMaxAge() == 0) {
            return false;
        }
        long createTime = data.getCreateTime();
        long deadTime = createTime + data.getMaxAge();
        long nowTime = new Date().getTime();
        if (nowTime > deadTime) {
            return true;
        }
        return false;
    }

    //移除
    public static boolean remove(String key) {
        cacheArea.remove(key);
        return true;
    }

    //缓存数据封装
    static class CacheData {

        //创建时间：单位毫秒
        private long createTime;
        //缓存时常：单位毫秒, 0-标示永不失效
        private long maxAge;
        //数据key
        private String key;
        //数据value
        private Object value;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(long maxAge) {
            this.maxAge = maxAge;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    //测试
    public static void main(String[] args) throws InterruptedException {
        SimpleLocalCache.put("key1", "v1", 1000);
        SimpleLocalCache.put("key2", "v2", 5000);
        SimpleLocalCache.put("key3", "v3", 0);
        Thread.sleep(3000);
        System.out.println(SimpleLocalCache.get("key1"));
        System.out.println(SimpleLocalCache.get("key2"));
        System.out.println(SimpleLocalCache.get("key3"));
    }
}
