package com.elvesyuki.javautils.normal.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ：luohuan
 * @date ：Created in 2022/3/22 10:30
 * @description：自定义id生成器
 * @modified By：
 */
@Component
public class IdGeneratorUtils {

    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorUtils.class);

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 初始化用，不要删除
     */
    @Resource
    private RedisUtils redisUtils;

    private static RedissonClient staticRedissonClient;

    private static TransactionTemplate staticTransactionTemplate;

    public static final String COMMON_ID_MODULE = "common";

    public static final String SYS_MODULE = "sys";

    public static final String USER_ID_MODULE = "user";

    public static final String LOG_ID_MODULE = "log";

    private static Map<String, LinkedBlockingQueue<Long>> idQueueMap = null;

    @PostConstruct
    public void init() {
        setStaticRedissonClient(redissonClient);
        setStaticTransactionTemplate(transactionTemplate);
        setIdQueueMap(new HashMap<>(4 * 2));
        replaceQueue(COMMON_ID_MODULE);
        replaceQueue(SYS_MODULE);
        replaceQueue(USER_ID_MODULE);
        replaceQueue(LOG_ID_MODULE);
    }


    public static Long generateId(String module) {
        LinkedBlockingQueue<Long> idQueue = idQueueMap.get(module);
        try {
            Long takeId = idQueue.take();
            logger.info("Generate id ==> {}", takeId);
            logger.info("idQueue-> key:{}, size:{}", module, idQueue.size());
            if (ObjectUtils.isEmpty(idQueue)) {
                // TODO 异步更新
                replaceQueue(module);
            }
            if (idQueue.remainingCapacity() > idQueue.size()) {
                // TODO 异步更新
                String counterRedisKey = "elves" + module;
                String stepRedisKey = "elves" + module;
                if (RedisUtils.checkExist(counterRedisKey) && RedisUtils.checkExist(stepRedisKey)) {
                    updateQueue(module);
                } else {
                    replaceQueue(module);
                }
            }
            return takeId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    private static void replaceQueue(String module) {
        // 分布式锁
        String redisLockName = "elves" + module;
        RLock lock = staticRedissonClient.getLock(redisLockName);
        lock.lock(30000, TimeUnit.MILLISECONDS);
        try {
            String counterRedisKey = "elves" + module;
            String stepRedisKey = "elves" + module;
            // 初始化id生成器的值,当前时间戳毫秒值加两位，一共十五位，小于js最大值9007199254740991 十六位
            long idCounter = System.currentTimeMillis() * 100;
            // 如果缓存存在，则从缓存中取值，正常情况下都应该从缓存中取，否则采用默认值
            if (RedisUtils.checkExist(counterRedisKey)) {
                // 获取缓存中的id生成器的值
                idCounter = Long.parseLong((String) RedisUtils.get(counterRedisKey));
            }
            // 默认step
            long idStep = 400;
            if (RedisUtils.checkExist(stepRedisKey)) {
                // 获取缓存中step
                idStep = Long.parseLong((String) RedisUtils.get(stepRedisKey));
            } else {
                // 如果缓存不存在就设置step
                RedisUtils.set(stepRedisKey, String.valueOf(idStep));
            }
            Long newCounter = idCounter + idStep;
            LinkedBlockingQueue<Long> idQueue = new LinkedBlockingQueue<>((int) (idStep * 2));
            for (long i = 0; i < idStep; i++) {
                if (idQueue.remainingCapacity() <= 0) {
                    break;
                }
                idQueue.put(idCounter + i);
            }
            idQueueMap.put(module, idQueue);
            RedisUtils.set(counterRedisKey, String.valueOf(newCounter));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }  finally {
            lock.unlock();
        }
    }

    private static void updateQueue(String module) {
        // 分布式锁
        String redisLockName = "elves" + module;
        RLock lock = staticRedissonClient.getLock(redisLockName);
        lock.lock(30000, TimeUnit.MILLISECONDS);
        try {
            LinkedBlockingQueue<Long> idQueue = idQueueMap.get(module);
            if (idQueue.remainingCapacity() > idQueue.size()) {
                String counterRedisKey = "elves" + module;
                String stepRedisKey = "elves" + module;
                long idCounter = Long.parseLong((String) RedisUtils.get(counterRedisKey));
                long idStep = Long.parseLong((String) RedisUtils.get(stepRedisKey));
                Long newCounter = idCounter + idStep;
                for (long i = 0; i < idStep; i++) {
                    if (idQueue.remainingCapacity() <= 0) {
                        break;
                    }
                    idQueue.put(idCounter + i);
                }
                RedisUtils.set(counterRedisKey, String.valueOf(newCounter));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    public static synchronized void setIdQueueMap(Map<String, LinkedBlockingQueue<Long>> idQueueMap) {
        IdGeneratorUtils.idQueueMap = idQueueMap;
    }

    public static synchronized RedissonClient getStaticRedissonClient() {
        return staticRedissonClient;
    }

    public static synchronized void setStaticRedissonClient(RedissonClient staticRedissonClient) {
        IdGeneratorUtils.staticRedissonClient = staticRedissonClient;
    }

    public static TransactionTemplate getStaticTransactionTemplate() {
        return staticTransactionTemplate;
    }

    public static void setStaticTransactionTemplate(TransactionTemplate staticTransactionTemplate) {
        IdGeneratorUtils.staticTransactionTemplate = staticTransactionTemplate;
    }
}
