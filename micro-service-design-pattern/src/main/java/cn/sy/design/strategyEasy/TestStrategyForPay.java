package cn.sy.design.strategyEasy;

public class TestStrategyForPay {
    public static void main(String[] args) {
        String inParam = "1000000";
        PayContext payContext;
        //使用支付宝支付
        payContext = new PayContext(new PayForAliPay());
        payContext.pay(inParam);

        //使用微信支付
        payContext.setStrategy(new PayForWechatPay());
        payContext.pay(inParam + "1");

        //使用银联支付
        payContext.setStrategy(new PayForUnionPay());
        payContext.pay(inParam + "2");
    }
}
