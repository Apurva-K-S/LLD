package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.ExactSplit;
import com.example.Splitwise.Entity.Split;
import com.example.Splitwise.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactSplitStrategyService implements SplitStrategyService{
    @Override
    public List<Split> computeSplit(double totalAmount, List<User> participants, Map<String, Double> metadata) {
        List<Split> splits = new ArrayList<>();
        for(User user: participants)
        {
            splits.add(new ExactSplit(user, metadata.get(user.getId())));
        }
        return splits;
    }
}
