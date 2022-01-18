package cn.sy.design.strategyHard1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class OnlineBuyChannelContext implements ApplicationContextAware {
    private Map<String,IStrategyHard1> BUY_CHANNEL_MAP = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String,IStrategyHard1> beanTypes = applicationContext.getBeansOfType(IStrategyHard1.class);
        log.info("beanTypes:{}",beanTypes);
        beanTypes.entrySet().forEach(entry->{
            BUY_CHANNEL_MAP.put(entry.getValue().getType(),entry.getValue());
        });
    }

    public IStrategyHard1 getBuyChannel(String type){
        if (StringUtils.isBlank(type)){
            throw  new RuntimeException("type is null");
        }

        return BUY_CHANNEL_MAP.get(type);
    }
}
