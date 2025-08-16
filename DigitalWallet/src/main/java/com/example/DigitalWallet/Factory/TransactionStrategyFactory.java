package com.example.DigitalWallet.Factory;

import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.Strategy.TransactionStrategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TransactionStrategyFactory {

    private final Map<TransactionType, TransactionStrategy> strategies = new EnumMap<>(TransactionType.class);

    public TransactionStrategyFactory(List<TransactionStrategy> strategyList) {
        for (TransactionStrategy s : strategyList) {
            strategies.put(s.getType(), s);
        }
    }

    public TransactionStrategy getStrategy(TransactionType type) {
        TransactionStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy registered for type: " + type);
        }
        return strategy;
    }
}
