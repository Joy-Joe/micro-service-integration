package cn.sy.design.strategyEasy;

import lombok.extern.slf4j.Slf4j;

/**
 * 银联支付
 */
@Slf4j
public class PayForUnionPay implements IStrategy{
    @Override
    public void pay(String inParam) {
        log.info(this.getClass().getSimpleName() + " pay starts");
        //组装支付调用入参，调用支付接口

    }
}
