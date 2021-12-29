package cn.sy.cache.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @Author joe
 * @Date 2021/12/29
 * @Desc 基于redis.clients JedisCluster实现分布式锁
 **/
@Component
public class RedisDistributeLock {

    public static final String NX = "NX";
    public static final String EX = "EX";
    public static final String OK = "OK";

    /**
     * 基于lua脚本删除key 保证原子操作
     */
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Resource
    private JedisCluster dataCheckRedisSentinel;

    private static final Long UNLOCK_OK = 1L;

    private static final ThreadLocal<String> uniqueValueTl = new ThreadLocal<>();

    private static final String lockKeyPrefix = "sylock:";

    /**
     * 加锁，必须调用unlock释放
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean tryLock(String key, int seconds) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uniqueValueTl.set(uuid);
        String isExist = dataCheckRedisSentinel.set(lockKeyPrefix.concat(key), uniqueValueTl.get(), NX, EX, seconds);
        return OK.equalsIgnoreCase(isExist);
    }

    public boolean unLock(String key) {
        try {
            Object unlock = dataCheckRedisSentinel.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKeyPrefix.concat(key)), Collections.singletonList(uniqueValueTl.get()));
            return UNLOCK_OK.equals(unlock);
        } finally {
            uniqueValueTl.remove();
        }
    }

    /**
     * 加锁，可以依赖自动过期
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean tryLockExpire(String key, int seconds, String value) {
        String isExist = dataCheckRedisSentinel.set(lockKeyPrefix.concat(key), value, NX, EX, seconds);
        return OK.equalsIgnoreCase(isExist);
    }

    public boolean unLockLastValue(String key, String oldValue) {
        Object unlock = dataCheckRedisSentinel.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKeyPrefix.concat(key)), Collections.singletonList(oldValue));
        return UNLOCK_OK.equals(unlock);
    }
}
