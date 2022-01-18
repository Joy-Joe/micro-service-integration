package cn.sy.design.strategyHard1;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TxBuyChannel implements IStrategyHard1{
    @Resource
    private CacheClusterService cacheClusterService;

    @Override
    public String buy() {
        return cacheClusterService.getValue("tx");
    }

    @Override
    public String getType() {
        return IStrategyHard1.StrategyType.TX.name();
    }
}
