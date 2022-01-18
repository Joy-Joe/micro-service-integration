package cn.sy.design.strategyEasy;

public class PayContext {
    public IStrategy strategy;

    public PayContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(String inParam){
        this.strategy.pay(inParam);
    }
}
