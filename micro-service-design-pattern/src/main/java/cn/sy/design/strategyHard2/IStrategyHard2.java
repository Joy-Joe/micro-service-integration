package cn.sy.design.strategyHard2;

public interface IStrategyHard2 {
    enum StrategyType{
        PDD,TX,DD,JD
    }

    String buy();
}
