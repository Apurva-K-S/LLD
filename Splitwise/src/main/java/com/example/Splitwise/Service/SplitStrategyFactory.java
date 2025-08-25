package com.example.Splitwise.Service;

import com.example.Splitwise.Enum.SplitType;

public class SplitStrategyFactory {
    public SplitStrategyService getStrategy(SplitType splitType)
    {
        switch (splitType){
            case EQUAL -> {
                return new EqualSplitStrategyService();
            }
            case EXACT -> {
                return new ExactSplitStrategyService();
            }
            case PERCENTAGE -> {
                return new PercentageSplitStrategyService();
            }
        }
        return null;
    }
}
