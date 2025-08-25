package com.example.Splitwise.Entity;

public class PercentageSplit extends Split{

    public PercentageSplit(User user, double amount) {
        super(user, amount);
    }

    @Override
    public String toString() {
        return "user=>"+super.getUser()+
                " amount=>"+super.getAmount();
    }
}
