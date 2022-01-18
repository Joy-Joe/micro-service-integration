package cn.sy.design.strategyHard1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheClusterService {

    public String getValue(String key){
        //模拟获取redis的值
        return key;
    }
}
