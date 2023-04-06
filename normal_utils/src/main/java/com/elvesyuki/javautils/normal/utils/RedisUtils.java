package com.elvesyuki.javautils.normal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ：luohuan
 * @date ：Created in 2022/3/22 9:59
 * @description：
 * @modified By：
 */
@Component
public class RedisUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public static RedisTemplate<String, Object> staticRedisTemplate;


    private static void setStaticRedisTemplate(RedisTemplate<String, Object> redisTemplate){
        RedisUtils.staticRedisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        setStaticRedisTemplate(redisTemplate);
    }

    /**
     * 查询是否存在某个key
     */
    public static boolean checkExist(String key) {
        return Boolean.TRUE.equals(RedisUtils.staticRedisTemplate.hasKey(key));
    }

    /**
     * 插入一个k-v
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key, Object value) {
        try {
            RedisUtils.staticRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除指定key
     *
     * @param key
     * @return -1：参数key数量为0或者为null，其他：删除数量
     */
    @SuppressWarnings({"unchecked"})
    public static long del(String... key) {
        if (null != key && key.length > 0) {
            if (key.length == 1) {
                RedisUtils.staticRedisTemplate.delete(key[0]);
                return 1L;
            } else {
                List<String> keyList = (List<String>) CollectionUtils.arrayToList(key);
                if (CollectionUtils.isEmpty(keyList)) {
                    return 0L;
                }
                return RedisUtils.staticRedisTemplate.delete(keyList);
            }
        }
        return -1L;
    }

    /**
     * 插入k-v，指定失效时间
     *
     * @param key
     * @param value
     * @param seconds 秒
     * @return
     */
    public static boolean setExpire(String key, Object value, long seconds) {
        try {
            RedisUtils.staticRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 如不存在k-v，则插入，并指定失效时间
     * @param key
     * @param value
     * @param milliSeconds 毫秒
     * @return
     */
    public static Boolean setIfAbsent(String key, Object value,long milliSeconds) {
        return RedisUtils.staticRedisTemplate.opsForValue().setIfAbsent(key, value,milliSeconds,TimeUnit.MILLISECONDS);
    }

    /**
     * 获取key的过期时间
     *
     * @param key
     * @return -2：key不存在，-1：无过期时间，其他：过期时间
     */
    public static long getExpire(String key) {
        return RedisUtils.staticRedisTemplate.getExpire(key, TimeUnit.SECONDS);

    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @return
     */
    public static boolean setExpire(String key, long seconds) {
        try {
            RedisUtils.staticRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取指定key对应的value
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return key == null ? null : RedisUtils.staticRedisTemplate.opsForValue().get(key);
    }

    /**
     * 指定的key增加或者减少指定的值，正数增加，负数减少
     *
     * @param key
     * @param incr
     * @return
     */
    public static long incr(String key, int incr) {
        return RedisUtils.staticRedisTemplate.opsForValue().increment(key, incr);
    }

    /**
     * 指定的key增加1
     *
     * @param key
     * @return
     */
    public static long incrOne(String key) {
        return RedisUtils.incr(key, 1);
    }

    /**
     * 指定的key减少1
     *
     * @param key
     * @return
     */
    public static long decrOne(String key) {
        return RedisUtils.incr(key, -1);
    }

    /**
     * 设置key过期时间
     * @param key
     * @param timeout 秒
     * @return
     */
    public static Boolean expire(String key,long timeout){
        return RedisUtils.staticRedisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }

    /**
     * 当newKey不存在时，重命名key
     * @param oldKey
     * @param newKey
     */
    public static void renameIfAbsent(String oldKey, String newKey){
        RedisUtils.staticRedisTemplate.renameIfAbsent(oldKey,newKey);
    }

    /**
     * 从右侧插入集合
     * @param key
     * @param collection
     * @return
     */
    public static Long lRightPushAll(String key, Collection<Object> collection){
        return RedisUtils.staticRedisTemplate.opsForList().rightPushAll(key,collection);
    }

    /**
     * 从左侧取出数据
     * @param key
     * @return
     */
    public static Object lLeftPop(String key){
        return RedisUtils.staticRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 获取list长度
     * @param key
     * @return
     */
    public static Long lSize(String key){
        return RedisUtils.staticRedisTemplate.opsForList().size(key);
    }

    /**
     * (hash)获取key对应hashKey的value
     *
     * @param key
     * @param hashKey
     * @return
     */
    public static Object hGet(String key, String hashKey) {
        return RedisUtils.staticRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @return
     */
    public static Map<Object, Object> hGetAll(String key) {
        return RedisUtils.staticRedisTemplate.opsForHash().entries(key);
    }

    /**
     * (hash)批量获取key对应hashKey的value
     * @param key
     * @param count
     * @return
     */
    public static Cursor<Map.Entry<Object, Object>> hScan(String key, long count){
        return RedisUtils.staticRedisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().count(count).build());
    }

    /**
     * 根据key获取Map
     * @param key
     * @return
     */
    public static Map<Object, Object> hEntries(String key){
        return RedisUtils.staticRedisTemplate.opsForHash().entries(key);
    }

    /**
     * (hash)将hashkey-value插入key
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public static void hPut(String key, String hashKey, Object value) {
        RedisUtils.staticRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * (hash)查询key是否有对应的hashKey
     *
     * @param key
     * @param hashKey
     * @return
     */
    public static Boolean hHasKey(String key, String hashKey) {
        return RedisUtils.staticRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * (hash)删除key指定的hashKey
     *
     * @param key
     * @param hashKey
     * @return
     */
    public static Long hDel(String key, String... hashKey) {
        return RedisUtils.staticRedisTemplate.opsForHash().delete(key, (Object) hashKey);
    }

    public static Long hDel(String key, String hashKey) {
        return RedisUtils.staticRedisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 指定的key增加或者减少指定的值，正数增加，负数减少
     * @param key
     * @param incr
     * @return
     */
    public static Long hIncr(String key, String hashKey, long incr) {
        return RedisUtils.staticRedisTemplate.opsForHash().increment(key, hashKey, incr);
    }

    /**
     * 指定的key增加1
     * @param key
     * @return
     */
    public static Long hIncrOne(String key,String hashKey) {
        return RedisUtils.hIncr(key, hashKey,1L);
    }

    /**
     * 指定的key减少1
     * @param key
     * @return
     */
    public static Long hDecrOne(String key,String hashKey) {
        return RedisUtils.hIncr(key, hashKey,-1L);
    }

    /**
     * (Set)集合添加元素
     * @param key
     * @return
     */
    public static Long sAdd(String key,Object value) {
        return RedisUtils.staticRedisTemplate.opsForSet().add(key,value);
    }

    /**
     * (Set)集合删除元素
     * @param key
     * @return
     */
    public static Long sDel(String key,Object value) {
        return RedisUtils.staticRedisTemplate.opsForSet().remove(key,value);
    }

    /**
     * (Set)集合是否存在value
     * @param key
     * @param value
     * @return
     */
    public static Boolean sIsMember(String key,Object value) {
        return RedisUtils.staticRedisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * (Set)获取集合所有成员
     * @param key
     * @return
     */
    public static Set<Object> sMembers(String key){
        return RedisUtils.staticRedisTemplate.opsForSet().members(key);
    }

    /**
     * (Set)获取对应集合大小
     * @param key
     * @return
     */
    public static Long sSize(String key) {
        return RedisUtils.staticRedisTemplate.opsForSet().size(key);
    }

    /**
     * (ZSet)插入value到key，并设置相应分数
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static Boolean zAdd(String key, Object value, double score){
        return RedisUtils.staticRedisTemplate.opsForZSet().add(key,value,score);
    }

    /**
     * (ZSet)删除key中对应的value
     *
     * @param key
     * @param value
     * @return
     */
    public static Long zDel(String key, Object... value){
        return RedisUtils.staticRedisTemplate.opsForZSet().remove(key,value);
    }

    /**
     * (ZSet)获取key-value对应的score
     *
     * @param key
     * @param value
     * @return
     */
    public static Double zScore(String key, Object value){
        return RedisUtils.staticRedisTemplate.opsForZSet().score(key,value);
    }

    /**
     * (ZSet)获取start-end间的value,排序按分值升序
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<Object> zRange(String key, long start, long end){
        return RedisUtils.staticRedisTemplate.opsForZSet().range(key,start,end);
    }

    /**
     * (ZSet)获取全部value,排序按分值升序
     *
     * @param key
     * @return
     */
    public static Set<Object> zAllRange(String key){
        long start = 0L;
        long end = -1L;
        return zRange(key,start,end);
    }

    /**
     * (ZSet)获取start-end间的value，排序按分值降序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<Object> zReverseRange(String key, long start, long end){
        return RedisUtils.staticRedisTemplate.opsForZSet().reverseRange(key,start,end);
    }

    /**
     * (ZSet)获取全部value，排序按分值降序
     *
     * @param key
     * @return
     */
    public static Set<Object> zAllReverseRange(String key){
        long start = 0L;
        long end = -1L;
        return zReverseRange(key,start,end);
    }

    /**
     * (ZSet)获取key中value数量
     *
     * @param key
     * @return
     */
    public static Long zSize(String key){
        return RedisUtils.staticRedisTemplate.opsForZSet().size(key);
    }

    /**
     * (ZSet)增加key-value元素分值
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static Double zIncScore(String key, Object value, double score){
        return RedisUtils.staticRedisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * (HyperLogLog)指定key添加元素
     * @param key
     * @param value
     * @return
     */
    public static Long pfAdd(String key, Object... value){
        return RedisUtils.staticRedisTemplate.opsForHyperLogLog().add(key,value);
    }

    /**
     * (HyperLogLog)获取key的基数估计值
     * @param key
     * @return
     */
    public static Long pfSize(String... key){
        return RedisUtils.staticRedisTemplate.opsForHyperLogLog().size(key);
    }

    /**
     * 查找匹配的key
     *
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern) {
        return RedisUtils.staticRedisTemplate.keys(pattern);
    }

}
