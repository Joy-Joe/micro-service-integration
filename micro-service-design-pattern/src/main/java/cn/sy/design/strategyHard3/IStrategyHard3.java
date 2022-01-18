package cn.sy.design.strategyHard3;

public interface IStrategyHard3 {
    enum StrategyType{
        PDD,TX,DD,JD
    }

    String buy();
}
