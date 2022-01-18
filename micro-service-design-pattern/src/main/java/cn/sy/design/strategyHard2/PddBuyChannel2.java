package cn.sy.design.strategyHard2;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class PddBuyChannel2 implements IStrategyHard2 {
    @Resource
    private CacheClusterService2 cacheClusterService2;

    @Resource
    private OnlineBuyChannelContext2 onlineBuyChannelContext2;

    @Override
    public String buy() {
        return cacheClusterService2.getValue("pdd");
    }


    @PostConstruct
    public void init(){
        onlineBuyChannelContext2.register(IStrategyHard2.StrategyType.PDD.name(),this);
    }
}
