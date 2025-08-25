package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.Split;
import com.example.Splitwise.Entity.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategyService {
    List<Split> computeSplit(double totalAmount, List<User> participants, Map<String, Double> metadata);
}
