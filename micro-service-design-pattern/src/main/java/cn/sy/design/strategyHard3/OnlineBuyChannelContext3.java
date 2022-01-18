package cn.sy.design.strategyHard3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class OnlineBuyChannelContext3 {
    private Map<String, IStrategyHard3> BUY_CHANNEL_MAP2 = new ConcurrentHashMap<>();

    public void register(String type, IStrategyHard3 iStrategyHard2Impl){
        if (StringUtils.isBlank(type)){
            throw  new RuntimeException("type is null");
        }
        BUY_CHANNEL_MAP2.put(type,iStrategyHard2Impl);

    }

    public IStrategyHard3 getBuyChannel(String type){
        if (StringUtils.isBlank(type)){
            throw  new RuntimeException("type is null");
        }

        return BUY_CHANNEL_MAP2.get(type);
    }
}
