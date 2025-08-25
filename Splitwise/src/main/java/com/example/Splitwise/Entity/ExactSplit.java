package com.example.Splitwise.Entity;

public class ExactSplit extends Split {
    public ExactSplit(User user,double amount) {
        super(user, amount);
    }

    @Override
    public String toString() {
        return "user=> "+super.getUser()+
                " amount=>"+super.getAmount();
    }
}
