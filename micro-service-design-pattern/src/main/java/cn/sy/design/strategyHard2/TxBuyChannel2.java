package cn.sy.design.strategyHard2;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class TxBuyChannel2 implements IStrategyHard2 {

    @Resource
    private CacheClusterService2 cacheClusterService2;

    @Resource
    private OnlineBuyChannelContext2 onlineBuyChannelContext2;

    @Override
    public String buy() {
        return cacheClusterService2.getValue("tx");
    }

    @PostConstruct
    public void init(){
        onlineBuyChannelContext2.register(StrategyType.TX.name(),this);
    }

}
