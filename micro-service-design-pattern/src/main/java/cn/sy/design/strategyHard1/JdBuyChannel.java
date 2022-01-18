package cn.sy.design.strategyHard1;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JdBuyChannel implements IStrategyHard1{
    @Resource
    private CacheClusterService cacheClusterService;

    @Override
    public String buy() {
        return cacheClusterService.getValue("jd");
    }

    @Override
    public String getType() {
        return StrategyType.JD.name();
    }
}
