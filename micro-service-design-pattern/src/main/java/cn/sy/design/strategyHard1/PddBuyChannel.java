package cn.sy.design.strategyHard1;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PddBuyChannel implements IStrategyHard1{
    @Resource
    private CacheClusterService cacheClusterService;

    @Override
    public String buy() {
        return cacheClusterService.getValue("pdd");
    }

    @Override
    public String getType() {
        return StrategyType.PDD.name();
    }
}
