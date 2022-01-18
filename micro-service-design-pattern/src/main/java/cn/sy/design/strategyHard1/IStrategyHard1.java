package cn.sy.design.strategyHard1;

public interface IStrategyHard1 {
    enum StrategyType{
        PDD,TX,DD,JD
    }
    String buy();
    String getType();
}
