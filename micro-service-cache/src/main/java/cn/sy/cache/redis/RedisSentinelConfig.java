package cn.sy.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * redis单点改为哨兵模式，统一使用配置类
 */
@Configuration
@Slf4j
public class RedisSentinelConfig {

    @Value("${sentinel.redis.pool.max-active}")
    int maxActive;
    @Value("${sentinel.redis.pool.max-idle}")
    int maxIdle;
    @Value("${sentinel.redis.pool.max-total}")
    int maxTotal;
    @Value("${sentinel.redis.pool.max-wait}")
    int maxWait;
    @Value("${sentinel.redis.pool.min-idle}")
    int minIdle;
    @Value("${sentinel.redis.pool.timeout}")
    int timeout;

    @Value("${sentinel.pool.maxWaitMillis}")
    private long maxWaitMillis;

    @Value("${sentinel.pool.splitPoint}")
    private String sentinelSplitPoint;

    /**
     * 创建哨兵 pool
     *
     * @param sentinelJedisNodes 哨兵节点
     * @return
     */
    public Pool createRedisPool(String masterName, String sentinelJedisNodes) {
        if (StringUtils.isEmpty(sentinelJedisNodes)) {
            return null;
        }
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMinIdle(minIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

            String[] nodes = sentinelJedisNodes.split(sentinelSplitPoint);
            List hosts = Arrays.asList(nodes);
            // 哨兵信息
            Set<String> sentinels = new HashSet<String>(hosts);
            //创建连接池
            //mymaster是我们配置给哨兵的服务名称
            JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig);
            return pool;
        } catch (Exception e) {
            throw e;
        }
    }

    @Value("${sentinel.pool.dataCheck.jedisNodes}")
    private String dataCheckJedisNodes;

    @Value("${sentinel.pool.dataCheck.masterName}")
    private String dataCheckMasterName;

    @Bean(name = "dataCheckRedisSentinel")
    public Pool beanDataCheckRedisPool() {
        return createRedisPool(dataCheckMasterName, dataCheckJedisNodes);
    }
}
