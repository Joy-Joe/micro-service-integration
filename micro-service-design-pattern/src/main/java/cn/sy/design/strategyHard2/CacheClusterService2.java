package cn.sy.design.strategyHard2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheClusterService2 {

    public String getValue(String key){
        //获取redis的值
        log.info("======123");
        return key;
    }
}