package com.example.Splitwise.Entity;

public class EqualSplit extends Split{
    public EqualSplit(User user, double amount) {
        super(user, amount);
    }

    @Override
    public String toString() {
        return "user=>"+super.getUser()+
                " amount=>"+super.getAmount();
    }
}
