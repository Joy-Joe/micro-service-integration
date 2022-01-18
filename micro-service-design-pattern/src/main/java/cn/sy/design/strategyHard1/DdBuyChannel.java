package cn.sy.design.strategyHard1;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DdBuyChannel implements IStrategyHard1{
    @Resource
    private CacheClusterService cacheClusterService;

    @Override
    public String buy() {
        return cacheClusterService.getValue("dd");
    }

    @Override
    public String getType() {
        return StrategyType.DD.name();
    }
}
