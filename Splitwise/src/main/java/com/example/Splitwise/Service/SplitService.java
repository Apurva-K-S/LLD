package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.Split;
import com.example.Splitwise.Entity.User;
import com.example.Splitwise.Enum.SplitType;

import java.util.List;
import java.util.Map;

public class SplitService {
    SplitStrategyFactory splitStrategyFactory;

    public SplitService(SplitStrategyFactory splitStrategyFactory)
    {
        this.splitStrategyFactory = splitStrategyFactory;
    }

    public List<Split> generateSplits(SplitType type, double totalAmount, List<User> participants, Map<String, Double> metadata) {
        SplitStrategyService strategy = splitStrategyFactory.getStrategy(type);
        return strategy.computeSplit(totalAmount, participants, metadata);
    }
}
