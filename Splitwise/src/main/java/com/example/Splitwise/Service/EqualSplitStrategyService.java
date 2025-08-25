package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.EqualSplit;
import com.example.Splitwise.Entity.Split;
import com.example.Splitwise.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategyService implements SplitStrategyService{
    public EqualSplitStrategyService() {
    }

    @Override
    public List<Split> computeSplit(double totalAmount, List<User> participants, Map<String, Double> metadata) {
        List<Split> splits = new ArrayList<>();
        double eachAmount = totalAmount/participants.size();
        for(User user : participants)
        {
            splits.add(new EqualSplit(user, eachAmount));
        }
        return splits;
    }
}
