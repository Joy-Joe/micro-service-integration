package cn.sy.design.strategyHard3;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JdBuyChannel3 implements IStrategyHard3, InitializingBean {
    @Resource
    private CacheClusterService3 cacheClusterService3;

    @Resource
    private OnlineBuyChannelContext3 onlineBuyChannelContext3;

    @Override
    public String buy() {
        return cacheClusterService3.getValue("jd");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        onlineBuyChannelContext3.register(StrategyType.JD.name(),this);
    }
}
